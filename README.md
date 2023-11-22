# Room Occupancy Manager
Spring Boot based application.

## Build

To build the project please use:
```
mvn clean package
```
This will produce ZIP artifact in ```/target``` directory, with below content:
```
├── config
│   ├── application-prod.yaml
│   ├── keystore.jks
│   ├── logback.xml
│   └── truststore.jks
├── logs
│   └── rom-main.log
└── rom-0.0.1-SNAPSHOT.jar
```

## Configure
App configuration is in ```/config/application.yaml``` file.
You can disable SSL client cert verification with 
```
server:
  ssl:
    enabled: false
```

## Run
In order to run the app, You need to pass spring profile as program argument and jasypt encryption key as VM option:
```
java -Djasypt.encryptor.password=secret -jar rom-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```
If You want to run tests, please use:
```
mvn test
```
If You want to run tests provided in task, please use ```OccupancyServiceTest``` test:
```
mvn test -Dtest=OccupancyServiceTest
```

## Postman SSL configuration
If You decide to not disable SSL client cert verification, You need to pass client certificate to requests.
Below is example with postman and ```client_cer.p12``` file which You can find in project base dir.

1. Setting / Certificates / Add Certificate...
2. Set HTTPS host and port on which You will run the app
3. Set ```client_cer.p12``` file as ```PFX file``` in Select file button
4. Set keystore passphrase to ```test```
5. Click Add button
6. Now all requests sent from Postman to host and port provided in step 2, will contain client certificate

## Application

### What can be added
- embedded database to store potential guests prices
- additional endpoints to set potential guests prices in database
- store potential guests prices per given day
- extend ```/occupancy/check``` endpoint with day parameter in order to calculate occupancy for given day
- add MDC key in logs to track requests
- add metrics to every request

### Quick framework/app additions
- logback with log rolling
- SSL with client auth
- health check actuator
- maven assembly plugin
- passwords encryption via jasypt