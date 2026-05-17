package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String tipo;      // Ejemplo: "PDF", "EXCEL"
    private String contenido;
}