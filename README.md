# bankApp
Simple Spring boot application which provide RESTful API for money transfer

### Prerequisite
- Maven
- JDK 1.11

### Project Structure
```bash
src
├── main
│   ├── java
│   │   └── ru
│   │       └── meshgroup
│   │           └── bankApplication
│   │               ├── config
│   │               ├── controllerAdvise
│   │               │   └── response
│   │               ├── dto
│   │               ├── exception
│   │               ├── mappers
│   │               ├── model
│   │               ├── repository
│   │               ├── rest
│   │               ├── security
│   │               ├── service
│   │               └── utils
│   └── resources
│       └── db.changelog
│           ├── init
│           ├── migrations
│           └── migrations-h2
└── test
    └── java
        └── ru
            └── meshgroup
                └── bankApplication
                    ├── rest
                    ├── security
                    ├── service
                    └── utils


```
### Packaging
```bash
mvn package
```
### Test
```bash
mvn test 
```
need Docker running
### Running
```bash
java -jar bankApplication-0.0.1-SNAPSHOT.jar 
```
### Profiles
| Profile 	| VM Options                     	| Usage                                 	
|---------	|--------------------------------	|---------------------------------------	
| dev     	| -Dspring.profiles.active=dev   	| Start with embedded H2                	
| redis   	| -Dspring.profiles.active=redis 	| Start with redis(need Docker running) 	 

### Docker

```bash
docker-compose -f ./src/main/resources/docker-compose.yaml up
```
need Docker running
### Data
Initial data (src/main/resources/db.changelog/changeLog.yaml) 
will be loaded when application start.

all users pass 123456789

### Flow

| Step           	| Url                                                                                 	|
|----------------	|-------------------------------------------------------------------------------------	|
| Create user    	| http://localhost:8080/swagger-ui/index.html#/1.%20User%20endpoints/createUser       	|
| Auth user      	| http://localhost:8080/swagger-ui/index.html#/2.%20Auth%20endpoints/login            	|
| Transfer money 	| http://localhost:8080/swagger-ui/index.html#/3.%20Account%20endpoints/transferMoney 	|
| Create email   	| http://localhost:8080/swagger-ui/index.html#/4.%20Email%20endpoints/postEmail       	|
| Update email   	| http://localhost:8080/swagger-ui/index.html#/4.%20Email%20endpoints/patchEmail      	|
