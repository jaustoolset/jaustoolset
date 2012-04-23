<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-2.0.xsd"
      default-init-method="initialize">

   <util:set id="persist-classes">
$classnames:{ cls |
      <value>$cls$</value> }$
     <value>com.u2d.model.AbstractComplexEObject_JTS</value>
     <value>com.u2d.generated.MessageType</value>
     <value>com.u2d.generated.DataFormat</value>
     <value>com.u2d.generated.SIUnit</value>
     <value>com.u2d.generated.PrimitiveType</value>
     <value>com.u2d.generated.PrimitiveTypeUnsigned</value>
     <value>com.u2d.generated.ConstType</value>
      <value>com.u2d.type.composite.Folder</value>
      <value>com.u2d.find.CompositeQuery</value>
      <value>com.u2d.type.composite.LoggedEvent</value>
      <value>com.u2d.app.User</value>
      <value>com.u2d.app.Role</value>
      <value>com.u2d.restrict.CommandRestriction</value>
      <value>com.u2d.restrict.FieldRestriction</value>
      <value>com.u2d.restrict.UserRestriction</value>
      <value>com.u2d.restrict.CreationRestriction</value>
      <value>com.u2d.restrict.Restriction</value>
      <value>com.u2d.reporting.SimpleReport</value>
      <value>com.u2d.element.ProgrammingElement</value>
      <value>com.u2d.element.Member</value>
      <value>com.u2d.element.Field</value>
      <value>com.u2d.element.Command</value>
      <value>com.u2d.field.CompositeField</value>
      <value>com.u2d.field.AtomicField</value>
      <value>com.u2d.field.AggregateField</value>
      <value>com.u2d.field.AssociationField</value>
      <value>com.u2d.field.IndexedField</value>
      <value>com.u2d.field.CompositeIndexedField</value>
      <value>com.u2d.element.EOCommand</value>
      <value>com.u2d.element.OverloadedEOCmd</value>
      <value>com.u2d.element.TypeCommand</value>
   </util:set>
   
</beans>
