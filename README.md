# PRUEBA LVL CONSULTING

## Descripción

Este proyecto es una aplicación desarrollada en Java utilizando Spring Boot, Maven y PostgreSQL. La aplicación permite
gestionar proyectos y tareas, incluyendo la creación, actualización, eliminación y filtrado de proyectos y tareas.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- `controller`: Contiene los controladores REST para manejar las solicitudes HTTP.
- `dto`: Contiene las clases de transferencia de datos (DTO) para las solicitudes y respuestas.
- `entity`: Contiene las entidades JPA que representan las tablas de la base de datos.
- `repository`: Contiene los repositorios JPA para acceder a la base de datos.
- `service`: Contiene las clases de servicio que implementan la lógica de negocio.
- `config`: Contiene las configuraciones de la aplicación, incluyendo la configuración de transacciones.

## Requisitos

- Java 17 o superior
- Maven 3.6.3 o superior
- PostgreSQL 12 o superior

## Configuración de la Base de Datos

Asegúrate de tener una base de datos PostgreSQL en funcionamiento y configura las credenciales en el archivo
`application.properties`:

```properties
# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=OmerSolutions
spring.datasource.password=peru2020
spring.datasource.driver-class-name=org.postgresql.Driver
# Hibernate configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=none
# Flyway configuration
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

## Ejecución

Para ejecutar la aplicación, utiliza el siguiente comando:

1. Clona el repositorio

```bash
git clone https://github.com/OmerJuve2023/MultipleJDBCClient.git
```

2. Compila y empaqueta el proyecto usando Maven

```bash
mvn clean install
```

3. Ejecuta la aplicación

```bash
mvn spring-boot:run
```
