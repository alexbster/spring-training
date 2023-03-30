# spring-training

## Ejercicio H2 

![Tablas de ejercicio](./img/Screen%20Shot%202023-03-16%20at%2019.21.44.png)

## Requisitos externos

- Docker
- docker run -p 27017:27017 --name mongodb -d mongo
- docker run -p 8280:8080 --name test-keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.0.1 start-dev
