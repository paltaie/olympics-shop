olympics-shop
=============

Olympics shop: for that quad-annual headache you get when you need to establish a Java EE olympics shop

# Building
Simply run `mvn clean install` at the top level of the project, which will build all components

# Usage
The app contains three runnable components:
- olyWeb: The olympics shop webapp
- olyWS: The olympics shop REST web service (Spring BOot application)
- olySupplier: The supplier GUI application

## To run olyWeb:
```
cd olyWeb
mvn clean install tomcat7:run
```

## To run olyWS:
```
cd olyWeb
mvn spring-boot:run
```

## To run olySupplier:
```
cd olySupplier
mvn exec:java -Dprop.file=olySupplier.properties
```