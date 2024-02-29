# README

## API de Números Aleatorios

Esta API fue creada con Java y Spring Boot y permite trabajar con números aleatorios. La API tiene los siguientes endpoints:

### GET /random/numbers

Este endpoint devuelve un listado de 100 números aleatorios.

Ejemplo de uso con cURL:

```bash
curl -X GET http://localhost:8080/random/numbers
```

### GET /random/number/{d}

Este endpoint devuelve un número aleatorio que tendrá `d` dígitos.

Ejemplo de uso con cURL:

```bash
curl -X GET http://localhost:8080/random/number/5
```

### PUT /random/number

Este endpoint recibe un número aleatorio (JSON en el body) y devuelve un número aleatorio (JSON en el body) con el mismo número de dígitos que el número recibido.

Ejemplo de uso con cURL:

```bash
curl -X PUT -H "Content-Type: application/json" -d '{"random":12345}' http://localhost:8080/random/number
```

## Modelo de Datos

El JSON para cada número aleatorio es:

```json
{ 
    "random": 12345 
}
```

## Cómo ejecutar la aplicación

1. Clona el repositorio en tu máquina local.
2. Navega hasta el directorio del proyecto.
3. Ejecuta el comando `./gradlew bootRun` para iniciar la aplicación.
4. La aplicación estará disponible en `http://localhost:8080`.

## Pruebas

Para ejecutar las pruebas, utiliza el comando `./gradlew test`.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue para discutir lo que te gustaría cambiar.

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)