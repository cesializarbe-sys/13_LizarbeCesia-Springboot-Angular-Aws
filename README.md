**Nombre del repositorio:**
```
crud-estudiantes-docker-aws
```

---

**README.md completo:**

```markdown
# CRUD Estudiantes — Spring Boot + Angular + Docker + AWS

Sistema web para gestión de estudiantes desarrollado con arquitectura de tres capas, contenerizado con Docker y desplegado en AWS EC2.

---

## Tecnologías utilizadas

- **Frontend:** Angular 17 + Nginx
- **Backend:** Spring Boot 3.5 + Java 17
- **Base de datos:** Microsoft SQL Server 2022
- **Contenedores:** Docker
- **Nube:** AWS EC2

---

## Estructura del repositorio

```
crud-estudiantes-docker-aws/
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/vallegrande/edu/pe/cesia/
│   │       │   ├── model/Estudiantes.java
│   │       │   ├── repository/EstudiantesRepository.java
│   │       │   ├── rest/EstudiantesRest.java
│   │       │   ├── service/EstudiantesService.java
│   │       │   └── CesiaApplication.java
│   │       └── resources/
│   │           └── application.yaml
│   ├── pom.xml
│   └── Dockerfile
├── frontend/
│   ├── src/app/
│   │   ├── app.component.ts
│   │   ├── app.component.html
│   │   ├── app.component.css
│   │   ├── app.module.ts
│   │   ├── estudiante.model.ts
│   │   └── estudiante.service.ts
│   └── Dockerfile
└── README.md
```

---

## Requisitos previos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Node.js 18+](https://nodejs.org/)
- [Angular CLI](https://angular.io/cli): `npm install -g @angular/cli`
- [Java 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/)

---

## Ejecución local (sin Docker)

### Paso 1 — Base de datos con Docker

```bash
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Cesia@12345" \
  -p 1433:1433 --name sqlserver \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

Conectarse al contenedor y crear la base de datos:

```sql
CREATE DATABASE EstudiantesDB;
GO

USE EstudiantesDB;
GO

CREATE TABLE Estudiantes (
  Id      INT IDENTITY(1,1) PRIMARY KEY,
  Nombre  NVARCHAR(100),
  Carrera NVARCHAR(100),
  Ciclo   INT
);
GO

INSERT INTO Estudiantes (Nombre, Carrera, Ciclo)
VALUES ('Mariana', 'Ingeniería de Sistemas', 5),
       ('Carlos', 'Ingeniería de Sistemas', 1);
GO
```

### Paso 2 — Backend

Editar `backend/src/main/resources/application.yaml` y cambiar la IP:

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=EstudiantesDB;encrypt=false;trustServerCertificate=true
    username: sa
    password: Cesia@12345
```

Correr el backend:

```bash
cd backend
mvn spring-boot:run
```

API disponible en: `http://localhost:8080/estudiantes`

### Paso 3 — Frontend

```bash
cd frontend
npm install
ng serve
```

App disponible en: `http://localhost:4200`

---

## Ejecución con Docker

### Base de datos

```bash
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Cesia@12345" \
  -p 1433:1433 --name sqlserver \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

### Backend

```bash
cd backend
docker build -t backend-estudiantes .
docker run -d -p 8080:8080 --name backend backend-estudiantes
```

### Frontend

```bash
cd frontend
docker build -t frontend-estudiantes .
docker run -d -p 80:80 --name frontend frontend-estudiantes
```

App disponible en: `http://localhost`

---

## Despliegue en AWS EC2

### Instancia de Base de datos

```bash
sudo apt update && sudo apt install docker.io -y
sudo systemctl start docker

sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Cesia@12345" \
  -p 1433:1433 --name sqlserver \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

### Instancia de Backend

Reemplazar `IP_SQL_SERVER` en `application.yaml` con la IP de la instancia de base de datos.

```bash
sudo apt update && sudo apt install docker.io -y
sudo systemctl start docker

sudo docker build -t backend-estudiantes .
sudo docker run -d -p 3000:3000 --name backend backend-estudiantes
```

### Instancia de Frontend

Reemplazar `IP_BACKEND` en el service de Angular con la IP de la instancia backend.

```bash
sudo apt update && sudo apt install docker.io -y
sudo systemctl start docker

sudo docker build -t frontend-estudiantes .
sudo docker run -d -p 80:80 --name frontend frontend-estudiantes
```

### Puertos a abrir en Security Groups de AWS

| Instancia | Puerto | Protocolo |
|-----------|--------|-----------|
| Base de datos | 1433 | TCP |
| Backend | 8080 | TCP |
| Frontend | 80 | TCP |

---

## Endpoints de la API

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /estudiantes | Listar todos los estudiantes |
| POST | /estudiantes | Registrar nuevo estudiante |
| PUT | /estudiantes/{id} | Actualizar estudiante |
| DELETE | /estudiantes/{id} | Eliminar estudiante |

### Ejemplo de body para POST y PUT

```json
{
  "nombre": "Ana García",
  "carrera": "Ingeniería de Sistemas",
  "ciclo": 3
}
```
