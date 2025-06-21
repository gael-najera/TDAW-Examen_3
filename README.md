# CRUD de Habitaciones usando AJAX -JQUERY - JSON - DATATABLE - MYSQL - SPRING BOOT

Este proyecto es una aplicación web para gestionar habitaciones, desarrollada con **Spring Boot**, **Thymeleaf**, **JDBC**, **jQuery/AJAX**, **Bootstrap** y **DataTables**.

## Funcionalidades

- Crear, editar y eliminar habitaciones.
- Búsqueda y visualización dinámica mediante AJAX.
- Interfaz moderna con Bootstrap y DataTables.
- Conexión a base de datos MySQL.

## Estructura del Proyecto

```
src
├── main
│   ├── java
│   │   └── com.upiiz.examen_3
│   │       ├── controllers
│   │       │   ├── HomeController.java
│   │       │   └── HabitacionController.java
│   │       ├── entities
│   │       │   
│   │       ├── models
│   │       │   └── HabitacionModel.java
│   │       ├── repositories
│   │       │   └── HabitacionRepository.java
│   │       ├── services
│   │       │   └── HabitacionService.java
│   │       └── Examen3Application.java
│   └── resources
│       ├── static
│       │   ├── js
│       │   │   └── crud-habitaciones.js
│       │   └── css
│       ├── templates
│       │   └── habitacion.html
│       └── application.properties
```

## Tecnologías Utilizadas

- Spring Boot 3.5.2
- Thymeleaf
- MySQL / JDBC
- Bootstrap 5
- jQuery & AJAX
- DataTables

## Configuración de Base de Datos

🧩 Base de Datos
Este proyecto utiliza una base de datos MySQL para almacenar la información de las habitaciones. A continuación se muestra la estructura de la tabla utilizada:

CREATE TABLE habitaciones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50),
    precio_noche DECIMAL(10,2),
    disponible BOOLEAN
);

id: Identificador único de la habitación.

tipo: Tipo de habitación (por ejemplo: individual, doble, suite, etc.).

precio_noche: Precio de la habitación por noche.

disponible: Indica si la habitación está disponible (true o false).

Esta tabla es accedida mediante Spring Data JDBC en el backend, con operaciones CRUD expuestas a través de una API REST.

En `application.properties`, asegúrate de configurar correctamente tu conexión:

```properties
spring.datasource.url=jdbc:mysql://<host>:<port>/<dbname>
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
```

> Puedes usar variables de entorno para las credenciales (`DB_USER`, `DB_PASSWORD`).

## Ejecución

1. Clona el repositorio.
2. Configura el archivo `application.properties`.
3. Ejecuta `Examen3Application.java` desde tu IDE o usando `mvn spring-boot:run`.
4. Accede a `http://localhost:8080`.

## Despliegue

Este proyecto puede desplegarse en servicios como **Render**. Asegúrate de exponer el puerto 8080 y definir las variables de entorno necesarias.

---

Desarrollado por: **Gael Nájera De Lira 4CM1**  
Instituto Politécnico Nacional - UPIIZ - Tecnologias para el Desarrollo de Aplicaciones Web