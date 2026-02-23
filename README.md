# BestGeek - Inventario API

**BestGeek** es una API REST diseñada para la gestión de inventarios, enfocada especialmente en categorías de productos y videojuegos. Este proyecto fue desarrollado como parte de una práctica técnica, demostrando el uso eficiente de Spring Boot y arquitecturas RESTful.

## Características

- **Gestión de Productos**: CRUD completo para el manejo de inventario físico.
- **Categorización**: Sistema flexible para organizar productos por tipo.
- **Videojuegos**: Entidad especializada con atributos propios del sector gaming.
- **Documentación Swagger**: Interfaz interactiva de API disponible en `/swagger-ui.html`.
- **Persistencia Dual**: Configurado para usar H2 en desarrollo y PostgreSQL en producción.
- **Seguridad y Validación**: Uso de `spring-boot-starter-validation` para integridad de datos.

## Tecnologías Utilizadas

- **Java 21**: Aprovechando las últimas características del lenguaje.
- **Spring Boot 4.x**: El núcleo de la aplicación.
- **Spring Data JPA**: Para la capa de persistencia y abstracción de datos.
- **SpringDoc OpenAPI**: Documentación automática de la API.
- **Lombok**: Reducción de código repetitivo.
- **Dotenv**: Gestión de variables de entorno segura.

## Requisitos

- JDK 21+
- Maven 3.8+
- Una instancia de PostgreSQL (opcional para desarrollo local con H2).

## Configuración y Ejecución

1.  Clona el repositorio.
2.  Configura el archivo `.env` en el directorio raíz (basado en `.env.example` si existe).
3.  Instala las dependencias y compila el proyecto:
    ```bash
    cd inventario-api
    ./mvnw clean install
    ```
4.  Inicia la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

## Endpoints Principales

- `GET /api/v1/productos`: Lista todos los productos.
- `GET /api/v1/categorias`: Lista todas las categorías.
- `GET /api/v1/videojuegos`: Lista el catálogo de juegos.

## Licencia

Este proyecto es de código abierto bajo la licencia MIT.
