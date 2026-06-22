## Systema de Punto de Venta para empresa El Regalo Perfecto S.A.

## Este proyectoes una aplicacion web completa para la gestion de puntos de venta construido con Springboot y Angular

Requisitos

- **Java JDK 17 o superior** (`java -version`)
- **Node.js (v18+) y npm** (`node -v` y `npm -v`)
- ## **MariaDB Server** activo y con las credenciales configuradas correspondientes.
  Pasos para iniciar

### 1.- Frontend

Ingresar al la ruta frontend/ y ejecutar en la terminal

````bash
   cd puntoVenta-project/frontend
   npm install
   npm run start o
   ng serve --host 0.0.0.0 --port 4200```
Acceder al aplicativo con ruta y el puerto dependiendo si se encuentra en local

### 2.- Backend
Ingresar al la ruta frontend/ y ejecutar en la terminal
```bash
   cd puntoVenta-project/backend
   ./mvnw spring-boot:run```

Agregar los puertos 4200 y 8080 a ufw
Conexion a MariaDB en /src/main/resources/application.properties

esta es una prueba de permisos en linux con win




````
