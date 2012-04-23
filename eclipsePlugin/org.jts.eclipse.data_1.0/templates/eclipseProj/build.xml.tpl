<project name="%project_name%" default="build">
	<property name="scons" location="%scons_loc%"/>
	<path id="classpath">
        <fileset dir="bin" includes="**/*.jar"/>
    </path>
    
	<target name="build" description="" depends="">
		<exec executable="${scons}"/>
	</target>
	
	<target name="clean" description="" depends="">
		<exec executable="${scons} -c"/>
	</target>
	<target name="run" depends="build">
		<java fork="true" classname="src.Main">
			<classpath>
				<path refid="classpath"/>
				<path location="bin/%project_name%.jar"/>
			</classpath>		
		</java>
	</target>
</project>