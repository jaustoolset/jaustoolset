<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-2.0.xsd"
      default-init-method="initialize">

   <util:set id="persist-classes">
$classnames:{ cls |
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="$cls$"/>
      </bean> }$
     <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.model.AbstractComplexEObject_JTS"/>
      </bean>
     <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.generated.MessageType"/>
      </bean>
     <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.generated.DataFormat"/>
      </bean>
     <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.generated.SIUnit"/>
      </bean>
     <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.generated.PrimitiveType"/>
      </bean>
     <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.generated.PrimitiveTypeUnsigned"/>
      </bean>
     <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.generated.ConstType"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.type.composite.Folder"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.find.CompositeQuery"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.type.composite.LoggedEvent"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.app.User"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.app.Role"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.restrict.CommandRestriction"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.restrict.FieldRestriction"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.restrict.UserRestriction"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.restrict.CreationRestriction"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.restrict.Restriction"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.reporting.SimpleReport"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.element.ProgrammingElement"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.element.Member"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.element.Field"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.element.Command"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.field.CompositeField"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.field.AtomicField"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.field.AggregateField"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.field.AssociationField"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.field.IndexedField"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.field.CompositeIndexedField"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.element.EOCommand"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.element.OverloadedEOCmd"/>
      </bean>
      <bean class="java.lang.Class" factory-method="forName">
         <constructor-arg value="com.u2d.element.TypeCommand"/>
      </bean>
   </util:set>
   
</beans>
