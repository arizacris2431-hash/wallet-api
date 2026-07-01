# 💳 Wallet API

API REST desarrollada con **Java 17** y **Spring Boot** para la gestión de billeteras digitales, permitiendo administrar usuarios, cuentas, categorías y transacciones mediante una arquitectura REST segura implementando autenticación y autorización con **Spring Security** y **JWT**.

---

# 🚀 Características

- ✅ Registro de usuarios
- ✅ Inicio de sesión con JWT
- ✅ Autenticación y autorización con Spring Security
- ✅ Gestión de cuentas
- ✅ Gestión de categorías
- ✅ Registro de ingresos y gastos
- ✅ Actualización automática de saldos
- ✅ Arquitectura en capas
- ✅ Manejo global de excepciones
- ✅ Persistencia con PostgreSQL

---

# 🛠 Stack Tecnológico

## Backend

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- Maven

## Base de datos

- PostgreSQL

## Herramientas

- Git
- GitHub
- Postman

---

# 🏗 Arquitectura

```
Cliente

     │

     ▼

REST API

     │

     ▼

Controllers

     │

     ▼

Services

     │

     ▼

Repositories

     │

     ▼

PostgreSQL
```

La aplicación sigue una arquitectura en capas, separando responsabilidades para facilitar el mantenimiento, la escalabilidad y las pruebas.

---

# 📂 Estructura del proyecto

```
src/main/java/com/wallet_api

├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
```

---

# 🔐 Seguridad

La autenticación se implementa mediante **Spring Security** y **JWT (JSON Web Token)**.

El acceso a los endpoints protegidos requiere enviar el token en el encabezado:

```http
Authorization: Bearer TU_TOKEN_JWT
```

---

# 📌 Funcionalidades principales

## Usuarios

- Registro
- Inicio de sesión
- Perfil del usuario

## Cuentas

- Crear cuentas
- Consultar cuentas
- Actualizar información

## Categorías

- Crear categorías
- Consultar categorías
- Actualizar categorías
- Eliminar categorías

## Transacciones

- Registrar ingresos
- Registrar gastos
- Consultar movimientos
- Actualización automática del saldo

---

# ⚙ Variables de entorno

La aplicación utiliza variables de entorno para proteger información sensible.

| Variable | Descripción |
|-----------|-------------|
| DATABASE_URL | URL de PostgreSQL |
| DATABASE_USERNAME | Usuario de PostgreSQL |
| DATABASE_PASSWORD | Contraseña de PostgreSQL |
| JWT_SECRET | Clave para firmar los JWT |
| JWT_EXPIRATION | Tiempo de expiración del token |

---

# ▶ Instalación

Clonar el proyecto

```bash
git clone https://github.com/arizacris2431-hash/wallet-api.git
```

Entrar al proyecto

```bash
cd wallet-api
```

Ejecutar

Linux / macOS

```bash
./mvnw spring-boot:run
```

Windows

```powershell
.\mvnw.cmd spring-boot:run
```

---

# 📸 Capturas

Agregar aquí imágenes de:

- Login
- Registro
- Postman
- Base de datos
- Endpoints

---

# 🚀 Próximas mejoras

- Documentación con Swagger/OpenAPI
- Docker
- Despliegue en Render
- Pruebas unitarias
- Integración continua (GitHub Actions)

---

# 👨‍💻 Autor

**Cristian Ariza Velasco**

Java Backend Developer

GitHub:
https://github.com/arizacris2431-hash

LinkedIn:
(Aquí agregas tu perfil)
