project(JausDissector)

cmake_minimum_required(VERSION 3.16)

find_package(Wireshark 3.2)
find_package(LibXml2)

INCLUDE(UseMakePluginReg.cmake)

find_package(PkgConfig REQUIRED)
pkg_search_module(GLIB2 REQUIRED glib-2.0)
include_directories (${GLIB2_INCLUDE_DIRS} ${Wireshark_INCLUDE_DIR} ${LIBXML2_INCLUDE_DIR})

set(LINK_MODE_LIB SHARED)
set(LINK_MODE_MODULE MODULE)
set(PLUGIN_VERSION "1.0.0")
add_definitions(-DPLUGIN_VERSION=\"${PLUGIN_VERSION}\")

set(DISSECTOR_SRC
	packet-jaus.c
	jausxml.c
)

set(PLUGIN_FILES
	plugin.c
	${DISSECTOR_SRC}
)

set_source_files_properties(
	${PLUGIN_FILES}
	PROPERTIES
	COMPILE_FLAGS "${WERROR_COMMON_FLAGS}"
)

register_plugin_files(plugin.c
	plugin
	${DISSECTOR_SRC}
)

add_library(jaus ${LINK_MODE_MODULE}
                  ${PLUGIN_FILES}
                  ${PLUGIN_RC_FILE}
)

set_target_properties(jaus PROPERTIES
		PREFIX ""
		LINK_FLAGS "${WS_LINK_FLAGS}"
		FOLDER "Plugins"
)

link_directories(${Wireshark_LIB_DIR})

target_link_libraries(jaus wireshark)