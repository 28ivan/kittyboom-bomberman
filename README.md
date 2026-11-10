# 💣 KittyBoom - Bomberman 2D en Java

<p align="center">
  <img src="BombermanGE/src/viewController/Imagenes/title.png" alt="KittyBoom Title" width="500"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/GUI-Java%20Swing%2FAWT-blue" alt="Swing"/>
  <img src="https://img.shields.io/badge/Architecture-MVC%20%26%20Design%20Patterns-green" alt="Architecture"/>
  <img src="https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-brightgreen?logo=githubactions&logoColor=white" alt="CI/CD"/>
  <img src="https://img.shields.io/badge/Status-Playable-success" alt="Status"/>
</p>

Recreación interactiva en 2D del clásico **Bomberman**, desarrollada en **Java** con interfaz gráfica **Swing/AWT**. El proyecto fue diseñado con un enfoque riguroso en **Arquitectura de Software**, aplicando **Patrones de Diseño GoF (Gang of Four)** y principios de Programación Orientada a Objetos (POO).

---

## 🎯 Patrones de Diseño Implementados

Este proyecto destaca por el desacoplamiento de su lógica y la modularidad mediante patrones clásicos de ingeniería del software:

- **🏭 Factory Method:** Creación dinámica de entidades según el tipo de bloque (`BloqueFactory`), tipo de bomba (`BombaFactory`) y personajes (`BomberManFactory`).
- **♟️ Strategy Pattern:** Algoritmos intercambiables para el comportamiento y destrucción de bloques (`StrategyBloques`) y tipos de explosión de bombas (`StrategyBombas`).
- **👀 Observer Pattern:** Sincronización desacoplada en tiempo real entre el estado interno del tablero (`model`) y el renderizado gráfico (`ObserverTableroPantalla` / `viewController`).
- **🔄 State Pattern:** Modelado y control de los diferentes estados de las casillas (`StateCasilla`).
- **🔒 Singleton:** Instancia única para la configuración y selección global de partidas (`Seleccion`).
- **🏛️ Arquitectura MVC:** Separación estricta entre la lógica del juego (`model`), controladores de eventos y renderizado en pantalla (`viewController`).

> **Diagramas de Software:** En la carpeta `BombermanGE/src/Diagramas/` se incluyen los diagramas de clases UML modelados durante el diseño del sistema.

---

## 🎮 Galería y Características del Juego

<p align="center">
  <img src="BombermanGE/src/viewController/Imagenes/seleccion.png" alt="Selección de Partida" width="600"/>
</p>

- **Selección de Personaje y Tablero:** Diferentes modalidades de partida con mapas configurables.
- **Entidades Dinámicas:** Bomberman blanco/negro, enemigos (mobs) y jefes con animaciones y estados.
- **Mecánicas Clásicas:** Colocación de bombas con temporizador, reacción en cadena de explosiones, destrucción de bloques blandos y obstáculos indestructibles.
- **Gráficos y Animaciones:** Sprites y animaciones para movimiento, daño, explosiones y victoria/derrota.

---

## 🚀 Cómo Jugar

### Opción 1: Ejecutable directo portable (.jar / .bat)
El proyecto incluye el archivo ejecutable **`Bomberman.jar`** y el lanzador **`Jugar.bat`**.
- **En Windows:** Simplemente haz doble clic sobre **`Jugar.bat`** para iniciar el juego al instante.
- **En cualquier sistema operativo (con Java 17+ instalado):**
  - Doble clic en `Bomberman.jar`
  - O desde la consola:
    ```bash
    java -jar Bomberman.jar
    ```

*(También puedes descargar el ejecutable recién compilado desde la pestaña **Actions** de GitHub en cada build).*

### Opción 2: Importar en un IDE (Eclipse, IntelliJ IDEA, VS Code)
1. Clona el repositorio:
   ```bash
   git clone https://github.com/28ivan/kittyboom-bomberman.git
   ```
2. Abre la carpeta del proyecto en tu IDE favorito.
3. Asegura que `BombermanGE/src` esté configurada como carpeta de fuentes (*Source Folder*).
4. Ejecuta la clase principal: `main.main` (`BombermanGE/src/main/main.java`).

---

## ⚙️ Integración Continua (CI/CD)

El repositorio cuenta con un pipeline automatizado en **GitHub Actions** (`.github/workflows/build.yml`) que en cada `push` o `pull request`:
1. Configura el entorno con OpenJDK 17.
2. Compila el código fuente verificando la integridad del proyecto.
3. Empaqueta el ejecutable `.jar` y lo publica automáticamente como un artefacto descargable.

---

## 👥 Autores y Créditos

Proyecto desarrollado en el marco del Grado en Ingeniería Informática.
- **Ivan Herrera** ([@28ivan](https://github.com/28ivan))
