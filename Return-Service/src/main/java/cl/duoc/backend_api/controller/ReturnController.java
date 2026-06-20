package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Return;
import cl.duoc.backend_api.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Devoluciones", description = "Operaciones de recepción de libros devueltos y auditoría de estados")
@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @Operation(summary = "Listar todas las devoluciones", description = "Retorna el historial completo de retornos y entregas procesadas.")
    @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente")
    @GetMapping
    public ResponseEntity<List<Return>> getAll() {
        return ResponseEntity.ok(returnService.findAll());
    }

    @Operation(summary = "Obtener una devolución por ID", description = "Busca un registro de devolución específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Registro encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Return> getById(@PathVariable Long id) {
        return returnService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar una nueva devolución", description = "Procesa la entrega de un libro asociándolo al ID del préstamo y evaluando las condiciones físicas en que regresa.")
    @ApiResponse(responseCode = "201", description = "Devolución registrada con éxito")
    @PostMapping
    public ResponseEntity<Return> create(@RequestBody Return returnEntity) {
        Return savedReturn = returnService.save(returnEntity);
        return new ResponseEntity<>(savedReturn, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una devolución", description = "Modifica los metadatos o corrige el estado físico de un libro en base a su ID.")
    @ApiResponse(responseCode = "200", description = "Registro actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado para modificar")
    @PutMapping("/{id}")
    public ResponseEntity<Return> update(@PathVariable Long id, @RequestBody Return returnEntity) {
        try {
            Return updatedReturn = returnService.update(id, returnEntity);
            return ResponseEntity.ok(updatedReturn);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un registro de devolución", description = "Remueve físicamente la auditoría de retorno del sistema utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = returnService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}