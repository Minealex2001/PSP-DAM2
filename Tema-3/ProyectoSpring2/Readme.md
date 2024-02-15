# Proyecto Spring Boot

Este proyecto es una aplicación web desarrollada con Spring Boot. La aplicación proporciona una API REST para gestionar pilotos de carreras.

## Tecnologías utilizadas

- Java
- Spring Boot
- Maven

## Estructura del proyecto

El proyecto sigue la estructura estándar de un proyecto Maven:

- `src/main/java`: Contiene el código fuente de la aplicación.
- `src/main/resources`: Contiene los recursos de la aplicación, como archivos de propiedades.
- `src/test`: Contiene los tests de la aplicación.
- `pom.xml`: Es el archivo de configuración de Maven.

## Código fuente

El código fuente de la aplicación se encuentra en el paquete `com.dam.proyectospring`. Este paquete contiene varios subpaquetes:

- `modelos`: Contiene las clases de modelo que representan las entidades de la aplicación.
- `repositorios`: Contiene las interfaces de repositorio que proporcionan operaciones CRUD para las entidades de la aplicación.
- `servicios`: Contiene las interfaces de servicio y sus implementaciones que proporcionan la lógica de negocio de la aplicación.
- `controladores`: Contiene los controladores que manejan las solicitudes HTTP a la API REST de la aplicación.

## Ejecución del proyecto

Para ejecutar el proyecto, necesitas tener instalado Java y Maven. Luego, puedes ejecutar el siguiente comando en la raíz del proyecto:

```
mvn spring-boot:run
```

Esto iniciará la aplicación en el puerto 8080. Puedes acceder a la API REST de la aplicación en `http://localhost:8080/api`.

## API REST

La API REST de la aplicación proporciona las siguientes rutas:

- `GET /api/pilotos`: Obtiene todos los pilotos.
- `POST /api/pilotos`: Crea un nuevo piloto.
- `GET /api/pilotos/{id}`: Obtiene un piloto por su id.
- `PUT /api/pilotos/{id}`: Actualiza un piloto existente.
- `DELETE /api/pilotos/{id}`: Elimina un piloto.

## Pruebas

Para ejecutar las pruebas de la aplicación, puedes ejecutar el siguiente comando en la raíz del proyecto:

```
mvn test
```

## Contribuir

Si deseas contribuir al proyecto, por favor, haz un fork del repositorio, crea una nueva rama, realiza tus cambios y envía un pull request.