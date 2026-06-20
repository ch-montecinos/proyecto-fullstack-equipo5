package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Notification;
import cl.duoc.backend_api.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Notificaciones", description = "Operaciones de envío y registro histórico de alertas a usuarios")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Operation(summary = "Listar todas las notificaciones", description = "Retorna el historial completo de alertas enviadas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente")
    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @Operation(summary = "Obtener notificación por ID", description = "Busca un registro de notificación específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Notificación encontrada")
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable Long id) {
        return notificationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear y enviar una notificación", description = "Registra una nueva alerta asignada a un usuario definiendo el canal (EMAIL/SMS) y el contenido.")
    @ApiResponse(responseCode = "201", description = "Notificación creada y registrada exitosamente")
    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Notification savedNotification = notificationService.save(notification);
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una notificación", description = "Modifica los datos de una alerta o cambia su contenido en base a su ID.")
    @ApiResponse(responseCode = "200", description = "Registro actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado para modificar")
    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(@PathVariable Long id, @RequestBody Notification notification) {
        try {
            Notification updatedNotification = notificationService.update(id, notification);
            return ResponseEntity.ok(updatedNotification);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un registro de notificación", description = "Remueve físicamente el registro del historial usando su ID.")
    @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = notificationService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}