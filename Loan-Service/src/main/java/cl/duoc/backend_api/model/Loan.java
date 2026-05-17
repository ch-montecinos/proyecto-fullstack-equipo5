package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idLibro;
    private Long idUsuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
}