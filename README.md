# proyecto-fullstack-equipo5
# Sistema Distribuido de Gestión Bibliotecaria

## 👥 Integrantes
* [Christian Montecinos]
* [Alejandro Naranjo]


---

## 🚀 Ecosistema de Microservicios

El sistema está compuesto por los siguientes microservicios desacoplados:

| Microservicio | Puerto Interno | Descripción |
| :--- | :---: | :--- |
| `api-gateway` | **8080** | Punto único de entrada y enrutamiento dinámico. |
| `user-service` | **8082** | Gestión de alumnos, bibliotecarios y roles. |
| `book-service` | **8083** | Catálogo general de libros y metadatos. |
| `loan-service` | **8084** | Motor central de transacciones y préstamos. |
| `inventory-service` | **8085** | Control de stock físico y copias. |
| `fine-service` | **8088** | Gestión y registro de penalizaciones financieras. |

---

## 📖 Documentación Viva (Swagger UI Links)

Con los servicios levantados localmente, puedes acceder a la documentación interactiva de la API en las siguientes direcciones:

*   **User Service:** [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)
*   **Book Service:** [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)
*   **Loan Service:** [http://localhost:8084/swagger-ui/index.html](http://localhost:8084/swagger-ui/index.html)

---

## 🛠️ Instrucciones de Ejecución

### Requisitos Previos
* Java 17 o superior.
* Docker y Docker Compose instalados.

### Pasos para levantar el entorno:

1. **Clonar el repositorio:**
```bash
   git clone https://github.com/ch-montecinos/proyecto-fullstack-equipo5
   cd entorno-desarrollo