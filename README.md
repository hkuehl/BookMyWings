[![Build Status](https://travis-ci.org/hkuehl/BookMyWings.svg?branch=master)](https://travis-ci.org/hkuehl/BookMyWings)

BookMyWings Application for PAC Sommer 2014
===========

## Environment-Setup ##

### System-requirements ###
- Java(TM) SE Runtime Environment (build 1.8.0-b132)
- WildFly 8.1.0.Final "Kenny"
- Apache Maven 3.1.1 (0728685237757ffbf44136acec0402957f723d9a; 2013-09-17 17:22:22+0200)

### Deployment-Package ###
Invoke ```mvn package ``` on the module [assembly-package](https://github.com/hkuehl/BookMyWings/tree/master/bmw-assembly) to generate an assembly-package that contains all deploy-artifacts.

### DataSource ###
WildFly has to be configured to provide a datasource named "java:jboss/datasources/BookMyWingsDS".
Please populate the connected database using the DDL-scripts contained in the [assembly-package](https://github.com/hkuehl/BookMyWings/tree/master/bmw-assembly) in folder ddl-sql.

### Securtiy (configured in standalone-full.xml) ###
WildFly has to be configured to provide a security-domain named "bmwsecdom". See example below.
```xml
<security-domain name="bmwsecdom" cache-type="default">
    <authentication>
        <login-module code="org.jboss.security.auth.spi.DatabaseServerLoginModule" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/BookMyWingsDS"/>
            <module-option name="principalsQuery" value="select password from PIL_PILOT where pilot_user_name=?"/>
            <module-option name="rolesQuery" value="select pilot_role, 'Roles' from PIL_ROLES r inner join pil_pilot p on r.id = p.pilotRole_id where p.pilot_user_name=?;"/>
            <module-option name="hashAlgorithm" value="SHA256"/>
        </login-module>
    </authentication>
</security-domain>
```
This security-domain has to be referenced from the following Realm:
```xml
<security-realm name="BookMyWingsRealm">
    <authentication>
        <jaas name="bmwsecdom"/>
    </authentication>
    <authorization>
        <properties path="application-roles.properties" relative-to="jboss.server.config.dir"/>
    </authorization>
</security-realm>
```
The role-based security differentiates two roles: "admin" and "user".



## Service-Architecture ##

### Naming and Structure ###
Maven-package-structure for a component always consists of a parent-project that is grouping a client-interface-project and the corresponding implementation-project.
Example:
```sh
bmw-aircraft
	\ bmw-aircraft-client
	\ bmw-aircraft-service
```
All Projects must start with the Book-My-Wings-identifier "bmw".

Entities have to explicitly define their table-name, which has to start using a component-prefix.
Example:
```java
@Table(name = "AIR_AIRCRAFT", uniqueConstraints = @UniqueConstraint(columnNames = { "registration" }))
public class Aircraft {
...
}
```
Method-names should correspond to the CRUD-naming-convention: Create, Read, Update, Delete.

Each entity provides its own URI in plural as entry-point for the ReST-API.
Example:
```
https://<server>:<port>/bookmywings/rest/aircrafts/
```

### Cross-references ###
Services are not allowed to reference other services' implementation - they may only reference their client-interface.

Non-functional or requirements that are or in future will be useful for all services can be referenced in the maven-module [common-service](https://github.com/hkuehl/BookMyWings/tree/master/bmw-common/common-service).

### Testing ###
Unit-Tests are supposed to reside in their corresponding entity-project.

Integration-Tests go into [bmw-integration-test](https://github.com/hkuehl/BookMyWings/tree/master/bmw-integration-test) and have to be implemented covering EJB, ReST and Dynamic-Proxy-calls.

### Validation ###
Basic Validation is done using the Validation-API. More Complex validation-scenarios may be implemented using a @Decorator or a custom ValidationService.

### Monitoring ###
Each service has to have the marker-interface [@Monitored](https://github.com/hkuehl/BookMyWings/blob/master/bmw-common/common-service/src/main/java/com/prodyna/bmw/server/common/monitoring/Monitored.java) to provide basic monitoring-data that can be processed in thrid-party-systems via JMX.