# Aplicación de Descarga y Compresión

Esta es una aplicación Java que permite descargar archivos de una URL proporcionada por el usuario y luego comprime estos archivos. La aplicación utiliza JavaFX para la gestión de eventos y listas.

## Requisitos

- Java 8 o superior
- Maven

## Instalación

1. Clona el repositorio en tu máquina local usando `git clone`.
2. Navega hasta el directorio del proyecto.
3. Ejecuta `mvn clean install` para construir el proyecto.

## Uso

1. Ejecuta la aplicación con `java -jar target/nombre-del-archivo.jar`.
2. Se te pedirá que ingreses la URL del archivo a descargar. Ingresa la URL sin el prefijo `https://`.
3. Presiona Enter para añadir la URL a la lista de descargas.
4. Si deseas añadir más URLs, repite el paso 2.
5. Cuando hayas terminado de añadir URLs, simplemente presiona Enter sin ingresar ninguna URL. La aplicación comenzará a descargar y comprimir los archivos.

## Documentación del código

El código está documentado con comentarios JavaDoc en español. Puedes generar la documentación HTML con `mvn javadoc:javadoc`.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o pull request para sugerencias de mejora o correcciones de errores.

## Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT.