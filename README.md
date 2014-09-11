ToDo-Wizard
========

A full-stack ToDo application using Dropwizard, AngularJS, Guice and Doma.

This project is configured as Gradle multi project.

## Requirements

- JDK8
- Node.js and npm

## Build setup

### UI

- ``npm install`` at todo-ui directory.

### Backend

- ``gradle todo-backend:oneJar`` at todowizard directory.

## Run application

TBD

## Limitations

An artifact is generated by Gradle one-jar plugin, but dropwizard-liquibase does NOT work.
