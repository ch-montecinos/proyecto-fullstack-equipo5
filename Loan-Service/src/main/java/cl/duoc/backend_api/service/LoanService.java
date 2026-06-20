package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Loan;
import cl.duoc.backend_api.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repo;

    private final WebClient webClient = WebClient.builder().build();

    // 1. Listar todos
    public List<Loan> obtenerTodos() {
        return repo.findAll();
    }

    // 2. Buscar por ID
    public Optional<Loan> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    // 3. Crear Préstamo 
    public Loan registrarPrestamo(Loan l) {
        System.out.println("Iniciando verificación inter-servicio para el préstamo...");

        // Comunicación con Inventory-Service
        try {
            String inventarioRespuesta = webClient.get()
                .uri("http://inventory-service:8084/api/inventory")
                .retrieve()
                .bodyToMono(String.class)
                .block();
            System.out.println("Conexión exitosa con Inventory. Respuesta: " + inventarioRespuesta);
        } catch (Exception e) {
            System.out.println("Inventory-service no respondió, continuando flujo por contingencia...");
        }

        // Comunicación con Fine-Service
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

        return repo.save(l);
    }

    // 4. Actualizar Préstamo 
    public Loan actualizarPrestamo(Long id, Loan nuevoLoan) {
        return repo.findById(id).map(prestamoExistente -> {
            prestamoExistente.setIdLibro(nuevoLoan.getIdLibro());
            prestamoExistente.setIdUsuario(nuevoLoan.getIdUsuario());
            prestamoExistente.setFechaPrestamo(nuevoLoan.getFechaPrestamo());
            prestamoExistente.setFechaDevolucion(nuevoLoan.getFechaDevolucion());
            return repo.save(prestamoExistente);
        }).orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    // 5. Eliminar Préstamo
    public boolean eliminarPrestamo(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}