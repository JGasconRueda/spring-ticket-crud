# Spring boot demo for API to manage the CRUD of a ticket

API REST services defined using Swagger/OpenAPI and Docker

## Entorno, Herramientas y dependencias destacadas
	
	IntelliJ IDEA 2021.2
	
	Maven 3.3
	Spring Boot 2.5.3
	Java 8
	Docker
	
	SpringBoot DevTools
	SpringBoot starter Data jpa
	H2 Database
	Lombok
	Mockito
	JUnit 5
	Springdoc OpenAPI

## Para compilar
```
mvn clean package -DskipTests
```
		

## Build Docker image
```
docker-compose build
```

## Arrancar Docker containers
```
docker-compose up
```

## Ver las imagenes creadas
```
docker image ls
```

## Eliminar imagen
```
docker image rm app-spring-ticket-crud
```