package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idUsuario;
    private Double monto;
    private String motivo;
    private String estado; // Ejemplo: "PENDIENTE" o "PAGADA"
}