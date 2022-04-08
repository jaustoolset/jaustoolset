README.txt

This has been built and tested (a little) on Ubuntu 20.04 with Wireshark 3.2.3. Please submit any issues to https://github.com/jaustoolset/jaustoolset/issues.

Beyond Wireshark, dependencies also include libxml2-dev.

To build:

1) Make a subdirectory under this one called build: mkdir build
2) Change to it: cd build
3) Run CMake: cmake ..
4) Compile: make
5) Manually copy jaus.so to <wireshark lib dir>/plugins/3.2/epan

For me <wireshark lib dir> = /usr/lib/x86_64-linux-gnu

To verify the plugin:

1) Open Wireshark
2) Select Help->About Wireshark
3) Verify that jaus.so is listed in the "Plugins" tab

Don't forget to copy the JSIDL (XML) files from packet-jaus-support/jausxml to /usr/local/lib/wirshark/packet-jaus-support/jausxml/
