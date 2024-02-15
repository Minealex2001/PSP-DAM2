# Persecución de Enemigos

## Descripción

Este es un proyecto de juego simple desarrollado en Java. En el juego, un jugador es perseguido por un número de enemigos en una cuadrícula. El jugador puede moverse en la cuadrícula usando las teclas W, A, S, D. Los enemigos se moverán hacia la posición del jugador cada vez que el jugador se mueva. El juego continúa hasta que el jugador decide detenerlo.

## Estructura del Proyecto

El proyecto consta de tres clases principales: `Jugador`, `Enemigo` y `Main`.

### Clase Jugador

La clase `Jugador` representa al jugador en el juego. Esta clase extiende `JFrame` e implementa `KeyListener`, lo que permite al jugador moverse en la cuadrícula usando las teclas del teclado. La posición del jugador se almacena en las variables `x` e `y`, y se actualiza cada vez que el jugador presiona una tecla. Además, la clase `Jugador` mantiene una lista de enemigos que se mueven cada vez que el jugador se mueve.

### Clase Enemigo

La clase `Enemigo` representa a los enemigos en el juego. Cada enemigo tiene una posición en la cuadrícula, almacenada en las variables `x` e `y`. Los enemigos se mueven hacia la posición del jugador cada vez que este se mueve.

### Clase Main

La clase `Main` es el punto de entrada del programa. Esta clase maneja la entrada del usuario y comienza el juego. Cuando el juego comienza, se crea un nuevo jugador y un número especificado de enemigos.

## Requisitos del sistema

- Java 8 o superior
- Maven

## Instalación

1. Clona el repositorio a tu máquina local usando `git clone`.
2. Navega hasta el directorio del proyecto.
3. Ejecuta `mvn clean install` para construir el proyecto.

## Ejecución

Para iniciar el juego, ejecuta `java -jar target/main.jar` desde la línea de comandos.

## Cómo jugar

El jugador puede moverse en la cuadrícula usando las siguientes teclas:

- W: Mover hacia arriba
- A: Mover hacia la izquierda
- S: Mover hacia abajo
- D: Mover hacia la derecha

Los enemigos se moverán hacia la posición del jugador cada vez que el jugador se mueva. El juego continúa hasta que el jugador decide detenerlo.

## Contribuir

Las contribuciones son bienvenidas. Si tienes sugerencias de cambios o mejoras, por favor, abre un problema o una solicitud de extracción.

## Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT.