ToDo-Wizard
========

A full-stack ToDo application using Dropwizard, AngularJS, Guice and Doma.

This project is configured as Gradle multi project.

## Requirements for Development

- JDK 8 (or Later)
- Lombok
- Node.js and npm

To running an application, only JRE(JDK)8 is required.

## Build setup

- ``./gradlew assemble`` at project root directory.

## Run application

1. ``java -jar todo-backend/build/libs/*-all.jar db migrate todo-backend/config/config.yml``
1. ``java -jar todo-backend/build/libs/*-all.jar server todo-backend/config/config.yml``

Try to access ``http://yourhost:18080/todo/``
