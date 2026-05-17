package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private Long idUsuario; // Relación con el User-Service
}