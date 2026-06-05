package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Loan;
import cl.duoc.backend_api.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repo;

    // Herramienta para hacer peticiones HTTP a otros microservicios
    private final WebClient webClient = WebClient.builder().build();

    public List<Loan> obtenerTodos() {
        return repo.findAll();
    }

    public Loan registrarPrestamo(Loan l) {
        System.out.println("Iniciando verificación inter-servicio para el préstamo...");

        // 1. COMUNICACIÓN CON INVENTORY-SERVICE: Simulamos verificar si hay stock
        // Usamos el nombre del contenedor de docker 'inventory-service' y su puerto interno 8084
        try {
            String inventarioRespuesta = webClient.get()
                .uri("http://inventory-service:8084/api/inventory")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // .block() hace que espere la respuesta para simular sincronía
            System.out.println("Conexión exitosa con Inventory. Respuesta: " + inventarioRespuesta);
        } catch (Exception e) {
            System.out.println("Inventory-service no respondió, continuando flujo por contingencia...");
        }

        // 2. COMUNICACIÓN CON FINE-SERVICE: Simulamos verificar si el usuario tiene multas
        try {
            String multasRespuesta = webClient.get()
                .uri("http://fine-service:8087/api/fines")
                .retrieve()
                .bodyToMono(String.class)
                .block();
            System.out.println("Conexión exitosa con Fines. Respuesta: " + multasRespuesta);
        } catch (Exception e) {
            System.out.println("Fine-service no respondió, continuando flujo por contingencia...");
        }

        // 3. Si las conexiones se efectúan, guardamos el préstamo en nuestra base de datos local
        return repo.save(l);
    }
}