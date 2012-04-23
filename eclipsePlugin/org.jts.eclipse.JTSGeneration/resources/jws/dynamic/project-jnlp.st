<?xml version="1.0" encoding="utf-8"?>

<jnlp spec="1.0+"
      codebase="http://$ip_addr$:$port$/message_set"
      href="project.jnlp">

 <information>
   <title>Project App</title> <!-- TODO: revise title appropriately -->
   <vendor>UptoData, Inc.</vendor> <!-- TODO: plug in vendor name here -->
   <homepage href="index.html" />
   <description kind="one-line">JAUSToolSet App</description>
   <description kind="short">JAUSToolSet App</description>
   <description kind="tooltip">JAUSToolSet App</description>
   <icon href="leaf.jpg" />
   <icon kind="splash" href="leaf.jpg" /> <!-- TODO: plug in splash image here -->
 </information>

 <security>
   <all-permissions />
 </security>

 <resources>
   <j2se version="1.5+" />
   <jar href="lib/swingvm.jar" main="true" />
   $jars:{jar|
   <jar href="$jar$" />
   }$
 </resources>

 <application-desc main-class="com.u2d.view.swing.EntryPoint">
   <argument>-Xdock:name=JAUSToolSet</argument>
   <argument>-Xdock:icon=icons.icns</argument>
 </application-desc>

</jnlp>

