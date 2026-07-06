# Sistema de Gestión de Biblioteca Distribuido (Microservicios)

## 👥 Integrantes
* **Christian Montecinos** 
* **Alejandro Naranjo** 

* **grupo 5** 

---

## 📝 Descripción del Dominio
Este proyecto consiste en una plataforma empresarial distribuida orientada a la automatización del ecosistema de una biblioteca digital y física. La arquitectura del sistema rompe el esquema monolítico tradicional mediante la implementación de microservicios autónomos y desacoplados. 

El dominio cubre los flujos esenciales de negocio: autenticación centralizada mediante tokens de seguridad (JWT), administración de perfiles de usuarios y roles, mantención de un catálogo automatizado de libros, control dinámico de inventario en tiempo real, registro transaccional de préstamos, y un módulo reactivo de devoluciones, multas y notificaciones. Todo el ecosistema está orquestado para operar de forma elástica bajo contenedores independientes que se comunican de manera nativa.

---

## 🧭 Listado de Microservicios e Infraestructura (Puertos)

El ecosistema se compone de los siguientes módulos distribuidos y sus respectivos entornos de datos aislados:

### Componentes de Infraestructura Central
* **`eureka-server`** (Puerto `8761`): Servidor de descubrimiento y registro dinámico de instancias en la nube.
* **`api-gateway`** (Puerto `8080`): Pasarela de entrada única y enrutador reactivo central del clúster.

### Microservicios de Negocio
* **`auth-service`** (Puerto `8081`): Control de acceso, emisión y validación de tokens JWT. Conectado a `db-auth`.
* **`user-service`** (Puerto `8082`): Gestión integrada de usuarios, credenciales y roles. Conectado a `db-users`.
* **`book-service`** (Puerto `8083`): Catálogo y almacenamiento del repositorio de libros. Conectado a `db-books`.
* **`loan-service`** (Puerto `8084`): Motor transaccional de solicitudes de préstamos y estados de reserva. Conectado a `db-loans`.
* **`inventory-service`** (Puerto `8085`): Control de stock y existencias físicas de obras por sucursal. Conectado a `db-inventory`.
* **`return-service`** (Puerto `8086`): Procesamiento y recepción de devoluciones de libros. Conectado a `db-return`.
* **`search-service`** (Puerto `8087`): Módulo optimizado para consultas y filtros avanzados en el catálogo. Conectado a `db-search`.
* **`fine-service`** (Puerto `8088`): Cálculo automático y registro de multas por entregas fuera de plazo. Conectado a `db-fine`.
* **`notification-service`** (Puerto `8089`): Despacho de alertas y confirmaciones transaccionales. Conectado a `db-notification`.
* **`report-service`** (Puerto `8090`): Generación de métricas y estadísticas de uso del sistema. Conectado a `db-report`.

---

## 🔀 Rutas Principales del API Gateway (Acceso desde Postman)

Para interactuar con el sistema desde el exterior (como Postman o aplicaciones cliente), todas las peticiones deben dirigirse a la **IP Pública de la instancia AWS EC2** a través del puerto expuesto por el **API Gateway (`8080`)**. 

Internamente, el Gateway utiliza el Service Discovery de **Eureka** para interceptar el prefijo de la URL y resolver dinámicamente el nombre lógico de cada microservicio en la red interna de Docker (`red_interna_proyecto`), abstrayendo por completo las IPs físicas y los puertos intermedios:

### 🔐 Módulo de Seguridad y Usuarios
* **Autenticación (Login):** * *Punto de Entrada Externo:* `POST http://[TU-IP-PÚBLICA-AWS]:8080/api/auth/login`
  * *Resolución Dinámica (Eureka):* `lb://auth-service`
* **Registro de Usuarios:** * *Punto de Entrada Externo:* `POST http://[TU-IP-PÚBLICA-AWS]:8080/api/users/register`
  * *Resolución Dinámica (Eureka):* `lb://user-service`

### 📚 Módulo de Catálogo e Inventario
* **Consultar Libros Disponibles:** * *Punto de Entrada Externo:* `GET http://[TU-IP-PÚBLICA-AWS]:8080/api/books`
  * *Resolución Dinámica (Eureka):* `lb://book-service`
* **Buscar Libro por ID:** * *Punto de Entrada Externo:* `GET http://[TU-IP-PÚBLICA-AWS]:8080/api/books/{id}`
  * *Resolución Dinámica (Eureka):* `lb://book-service`
* **Verificar Stock:** * *Punto de Entrada Externo:* `GET http://[TU-IP-PÚBLICA-AWS]:8080/api/inventory/check/{bookId}`
  * *Resolución Dinámica (Eureka):* `lb://inventory-service`

### 💳 Módulo de Operaciones Transaccionales