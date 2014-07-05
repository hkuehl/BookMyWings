[![Build Status](https://travis-ci.org/hkuehl/BookMyWings.svg?branch=master)](https://travis-ci.org/hkuehl/BookMyWings)

BookMyWings
===========

BookMyWings Application for PAC Sommer 2014

Environment-Setup

DataSource
WildFly has to be configured to provide a datasource named "java:jboss/datasources/BookMyWingsDS".
Please populate the connected database using the DDL-scripts containd in the assembly-package in folder ddl-sql.

Securtiy (configured in standalone-full.xml)
WildFly has to be configured to provide a security-domain named "bmwsecdom". See example below.
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

This security-domain has to be referenced from the following Realm:
			<security-realm name="BookMyWingsRealm">
                <authentication>
                    <jaas name="bmwsecdom"/>
                </authentication>
                <authorization>
                    <properties path="application-roles.properties" relative-to="jboss.server.config.dir"/>
                </authorization>
            </security-realm>

The role-based security differentiates two roles: "admin" and "user".


=================================
Service-Architecture

Maven-package-structure for a component always consists of a parent-project that is grouping a client-interface-project and the corresponding implementation-project.
Example:
bmw-aircraft
	\ bmw-aircraft-client
	\ bmw-aircraft-service

All Projects must start with the Book-My-Wings-identifier "bmw".

Services are not allowed to reference other services' implementation - they may only reference their client-interface.

Non-functional or requirements that are or in future will be useful for all services can be referenced in the maven-module "common-service".

Unit-Tests are supposed to reside in their corresponding entity-project.
Integration-Tests go into "bmw-integration-test" and have to be implemented covering EJB, ReST and Dynamic-Proxy-calls.

Basic Validation is done using the Validation-API. More Complex validation-scenarios may implemented using a @Decorator or a custom ValidationService.

Each service has to have the marker-interface "com.prodyna.bmw.server.common.monitoring.Monitored" to provide basic monitoring-data that can be processed in thrid-party-systems via JMX.