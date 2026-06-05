 package cl.duoc.backend_api.model;



import jakarta.persistence.*;

import lombok.Data;



@Entity

@Data

public class Notification {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

   

    private Long idUsuario;

    private String mensaje;

    private String tipo; // Ejemplo: "EMAIL" o "SMS"

}