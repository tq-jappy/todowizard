ToDo-Wizard
========

A full-stack ToDo application using Dropwizard, AngularJS, Guice and Doma.

This project is configured as Gradle multi project.

## Requirements for Development

- JDK8
- Lombok
- Gradle
- Node.js and npm

To running an application, only JRE(JDK)8 is required.

## Build setup

### UI

- ``npm install``

### Backend

- ``gradle todo-backend:oneJar``

## Run application

TBD

Try to access ``http://yourhost:18080/todo/``

## Limitations

This application is packaged as "One-Jar" files — single .jar files which contain all of the .class files required to run your application.

But dropwizard-liquibase wont work in this style.  

see:  
http://stackoverflow.com/questions/15940806/using-dropwizard-migrations-could-not-find-implementation-of-liquibase-logging
https://liquibase.jira.com/browse/CORE-975

