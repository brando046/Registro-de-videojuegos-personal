# Registro de Videojuegos Personales

## Descripción

Registro de Videojuegos Personales es una aplicación de escritorio desarrollada en Java que permite llevar un registro de los videojuegos jugados, almacenando información sobre su nombre, plataforma, género y horas de juego.

El proyecto permite consultar la lista de videojuegos registrados y obtener un resumen de las horas jugadas por género. Los datos se guardan en un archivo para conservar la información entre ejecuciones.

Este proyecto forma parte de mi aprendizaje en el ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM).

## Tecnologías utilizadas

- **Java:** desarrollo de la aplicación y programación orientada a objetos.
- **Java Swing:** creación de la interfaz gráfica.
- **Serialización de objetos:** almacenamiento y recuperación de los datos.
- **Git y GitHub:** control de versiones y alojamiento del código.

## Funcionalidades

- Añadir videojuegos indicando nombre, plataforma, género y horas jugadas.
- Seleccionar el género entre Acción, RPG, Deportes, Aventura y Otros.
- Validar los campos y comprobar que las horas introducidas sean válidas.
- Consultar la lista de videojuegos registrados.
- Obtener un resumen de las horas jugadas por género.
- Guardar los datos automáticamente en un archivo.
- Recuperar los datos guardados al iniciar la aplicación.

## Estructura del proyecto

```text
Registro-de-videojuegos-personal/
├── src/
│   ├── AppVideojuegos.java
│   ├── GestorDatos.java
│   ├── InterfazVideojuegos.java
│   └── Videojuego.java
├── videojuegos.dat
└── README.md
```

### Organización del código

- **AppVideojuegos.java:** contiene el método principal y pone en marcha la aplicación.
- **GestorDatos.java:** gestiona la colección de videojuegos y la lectura y escritura de los datos.
- **InterfazVideojuegos.java:** implementa la ventana principal y los controles de la interfaz gráfica.
- **Videojuego.java:** representa cada videojuego mediante sus atributos y métodos.
- **videojuegos.dat:** archivo utilizado para almacenar los datos mediante serialización.

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/brando046/Registro-de-videojuegos-personal.git
```

### 2. Abrir el proyecto

Abre la carpeta del proyecto con un IDE compatible con Java, como IntelliJ IDEA, Eclipse o NetBeans.

Asegúrate de tener instalado el JDK.

### 3. Ejecutar la aplicación

Localiza el archivo `AppVideojuegos.java` dentro de `src` y ejecuta su método `main`.

También puedes compilar y ejecutar desde una terminal situada dentro de la carpeta `src`:

```bash
javac Videojuego.java GestorDatos.java InterfazVideojuegos.java AppVideojuegos.java
java AppVideojuegos
```

## Almacenamiento de datos

La aplicación utiliza serialización de objetos para guardar la información en `videojuegos.dat`. De esta forma, los videojuegos registrados se pueden recuperar cuando se vuelve a iniciar el programa.

Si eliminas o sustituyes este archivo, podrías perder los datos almacenados. Si decides publicarlo en GitHub, revisa antes su contenido y evita incluir información personal que no quieras compartir.


