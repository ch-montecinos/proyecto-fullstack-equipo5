package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String consulta;    // Lo que el usuario escribió
    private String filtro;      // Ejemplo: "AUTOR", "TITULO", "ISBN"
    private String fechaBusqueda;
}