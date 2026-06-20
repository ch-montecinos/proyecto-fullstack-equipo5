package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Fine;
import cl.duoc.backend_api.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Multas", description = "Operaciones de control, penalizaciones y estados de cuentas de usuarios")
@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineService fineService;

    @Operation(summary = "Listar todas las multas", description = "Retorna la lista completa de penalizaciones registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de multas obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Fine>> getAll() {
        List<Fine> multas = fineService.obtenerTodas();
        return ResponseEntity.ok(multas);
    }

    @Operation(summary = "Obtener una multa por su ID", description = "Busca una penalización específica en el registro usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Multa encontrada exitosamente")
    @ApiResponse(responseCode = "404", description = "Multa no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Fine> getById(@PathVariable Long id) {
        return fineService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar una nueva multa", description = "Crea una penalización asignada a un usuario con su monto, motivo y estado inicial.")
    @ApiResponse(responseCode = "201", description = "Multa registrada exitosamente")
    @PostMapping
    public ResponseEntity<Fine> create(@RequestBody Fine fine) {
        Fine nuevaMultas = fineService.guardarFine(fine);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMultas);
    }

    @Operation(summary = "Actualizar una multa existente", description = "Modifica los datos o cambia el estado (ej. de PENDIENTE a PAGADA) de una multa en base a su ID.")
    @ApiResponse(responseCode = "200", description = "Multa actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Multa no encontrada para actualizar")
    @PutMapping("/{id}")
    public ResponseEntity<Fine> update(@PathVariable Long id, @RequestBody Fine fine) {
        try {
            Fine multaActualizada = fineService.actualizarFine(id, fine);
            return ResponseEntity.ok(multaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una multa", description = "Remueve físicamente el registro de la multa del sistema utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Multa eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Multa no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = fineService.eliminarFine(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}