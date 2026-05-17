package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "returns_log") // <-- ESTA LÍNEA ES LA MAGIA. Evita usar la palabra reservada 'return'
@Data
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idPrestamo;
    private String fechaDevolucion;
    private String estadoLibro;
}