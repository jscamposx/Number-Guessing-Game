# Guess Gaming 🎮

¡Bienvenido a Guess Gaming! Un simple juego de adivinar el número implementado con Spring Boot y Spring Shell donde pones a prueba tu suerte y lógica.

## 🚀 Tecnologías

Este proyecto está construido utilizando las siguientes tecnologías:

* 🍃 **Spring Boot:** Framework principal para la creación rápida de aplicaciones.
* ☕ **Java 21:** Última versión LTS de Java utilizada para el desarrollo.
* 🧱 **Maven:** Herramienta para la gestión de dependencias y construcción del proyecto.
* 🐚 **Spring Shell:** Para crear una interfaz de línea de comandos (CLI) interactiva.

## 📂 Estructura del Proyecto

La estructura general del proyecto es la siguiente:

```bash
Number_Guessing/
├── pom.xml                     # 📄 Archivo de configuración de Maven
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── Number_Guessing/
    │   │           ├── NumberGuessingApplication.java  # ▶️ Punto de entrada de la aplicación
    │   │           ├── cli/
    │   │           │   └── GameCLI.java                # ⌨️ Define los comandos de la consola
    │   │           ├── config/
    │   │           │   └── GameConfig.java             # ⚙️ Configuración del rango de números
    │   │           ├── model/
    │   │           │   ├── GameState.java              # 📊 Representa el estado actual del juego (número secreto, intentos, etc.)
    │   │           │   └── GuessResult.java            # 🎯 Resultado de un intento (alto, bajo, correcto)
    │   │           ├── service/
    │   │           │   └── GameService.java            # <0xF0><0x9F><0xA7><0xAD> Lógica central del juego (procesar intentos)
    │   │           └── utils/
    │   │               └── HintGenerator.java          # 🤔 Generador de pistas aleatorias
    │   └── resources/
    │       └── application.properties      # 🔧 Propiedades (ej: rango de números)
    └── test/                               # 🧪 (Directorio para pruebas unitarias)
        └── java/
```

## 💻 Entradas y Salidas de la Terminal
Una vez que la aplicación está corriendo, puedes interactuar con ella a través de la terminal. Aquí algunos ejemplos:

Mensaje de Bienvenida al iniciar:

```Bash

shell:>
🎉 ¡Bienvenido a Guess Gaming!
Este es un juego donde debes adivinar un número entre 1 y 100. # (El rango puede cambiar)
Según la dificultad que elijas, tendrás un número limitado de intentos para adivinar el número correcto.

Para comenzar, usa el comando 'start <dificultad>' con una de las siguientes opciones:
- easy: 10 intentos
- medium: 5 intentos
- hard: 3 intentos

Una vez que el juego haya comenzado, usa el comando 'guess <número>' para intentar adivinar el número.
Si necesitas ayuda, puedes escribir 'hint' para recibir una pista.
¡Buena suerte!
```
Iniciar un juego nuevo (ej: dificultad media):

```Bash

shell:> start medium
🎉 Nuevo juego iniciado.
Estoy pensando en un número entre 1 y 100.
Dificultad: MEDIUM (5 intentos).
Usa el comando 'guess <número>' para intentar.
Usa 'hint' si quieres una pista.
```
Hacer un intento:

```Bash

shell:> guess 50
🔼 El número es mayor que 50.
```
```Bash

shell:> guess 80
🔽 El número es menor que 80.
```
Pedir una pista:

```Bash

shell:> hint
🔍 El número está entre 70 y 90. # (La pista y el tipo de pista varían)
```


```Bash

shell:> hint
🔢 El número es par.

```
Verificar el estado actual:

```Bash

shell:> status
Intentos usados: 3 / 5

```
Resultado final (Ganar):

```Bash

shell:> guess 76 # Suponiendo que 76 es el número secreto
🎉 ¡Correcto! Adivinaste el número en 4 intentos.

```
Resultado final (Perder):

```Bash

shell:> guess 90 # Si es el último intento y es incorrecto
❌ ¡Se acabaron los intentos! El número era: 76

```
Intentar jugar después de terminar:

```Bash

shell:> guess 10
⚠️ El juego ha terminado. Usa 'start' para iniciar otro.
▶️ Cómo ejecutar el Jar
```
## Para ejecutar el juego desde el archivo JAR empaquetado:

```Bash

mvn clean package
Ejecutar el JAR:
```
Una vez que el JAR se haya creado (por ejemplo, Number_Guessing-0.0.1-SNAPSHOT.jar), ejecútalo con Java:

```Bash

java -jar target/Number_Guessing-0.0.1-SNAPSHOT.jar
```
(Asegúrate de reemplazar Number_Guessing-0.0.1-SNAPSHOT.jar con el nombre exacto del archivo generado en tu directorio target/).

¡La aplicación se iniciará en tu terminal y podrás empezar a jugar! 🎉