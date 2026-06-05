package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.User;
import cl.duoc.backend_api.service.UserService; // IMPORTAMOS EL NUEVO SERVICE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService; // CAMBIADO: Ahora el controlador llama al Service

    @GetMapping
    public ResponseEntity<List<User>> listar() {
        // El controlador le pide los usuarios al service y responde con 200 OK
        List<User> usuarios = userService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<User> crear(@RequestBody User u) {
        // El controlador delega el guardado a la capa de negocio
        User usuarioCreado = userService.guardarUsuario(u);
        
        // Devolvemos el estatus 201 Created que exige la comisión para las inserciones
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }
}