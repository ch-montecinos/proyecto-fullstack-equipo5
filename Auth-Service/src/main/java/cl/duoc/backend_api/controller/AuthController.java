package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Auth;
import cl.duoc.backend_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Autenticación", description = "Operaciones de gestión de credenciales y acceso de usuarios")
@RestController
@RequestMapping("/api/auth")
public class AuthController { 

    @Autowired
    private AuthService authService;

    @Operation(summary = "Listar todas las credenciales", description = "Retorna la lista completa de registros de autenticación en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Auth>> listar() { 
        return ResponseEntity.ok(authService.findAll()); 
    }

    @Operation(summary = "Obtener credenciales por ID", description = "Busca un registro de autenticación específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Registro encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Auth> obtenerPorId(@PathVariable Long id) {
        return authService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nuevas credenciales", description = "Crea un nuevo acceso de usuario con su nombre de usuario, contraseña e ID de usuario asociado.")
    @ApiResponse(responseCode = "201", description = "Credenciales registradas exitosamente")
    @PostMapping
    public ResponseEntity<Auth> registrar(@RequestBody Auth a) { 
        Auth savedAuth = authService.save(a);
        return new ResponseEntity<>(savedAuth, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar credenciales existentes", description = "Modifica el usuario, contraseña o ID de usuario asociado en base a su ID.")
    @ApiResponse(responseCode = "200", description = "Credenciales actualizadas exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado para actualizar")
    @PutMapping("/{id}")
    public ResponseEntity<Auth> actualizar(@PathVariable Long id, @RequestBody Auth a) {
        try {
            Auth authActualizado = authService.actualizar(id, a);
            return ResponseEntity.ok(authActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar credenciales", description = "Elimina de forma física las credenciales del sistema usando su ID.")
    @ApiResponse(responseCode = "204", description = "Credenciales eliminadas exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = authService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}