package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Inventory;
import cl.duoc.backend_api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Inventario", description = "Operaciones de control de existencias, stock y disponibilidad de libros")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Operation(summary = "Listar todo el inventario", description = "Retorna la lista completa del stock registrado en el sistema.")
    @ApiResponse(responseCode = "200", description = "Inventario obtenido exitosamente")
    @GetMapping
    public ResponseEntity<List<Inventory>> listar() {
        List<Inventory> stock = inventoryService.obtenerTodos();
        return ResponseEntity.ok(stock);
    }

    @Operation(summary = "Obtener stock por ID de Registro", description = "Busca un registro de inventario específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Registro de inventario encontrado")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> obtenerPorId(@PathVariable Long id) {
        return inventoryService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nuevo stock", description = "Asocia una cantidad de existencias iniciales a un ID de libro específico.")
    @ApiResponse(responseCode = "201", description = "Stock registrado exitosamente")
    @PostMapping
    public ResponseEntity<Inventory> guardar(@RequestBody Inventory i) {
        Inventory stockGuardado = inventoryService.guardarInventory(i);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockGuardado);
    }

    @Operation(summary = "Actualizar stock existente", description = "Modifica la cantidad o el ID de libro asignado en base al ID del registro.")
    @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado para actualizar")
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> actualizar(@PathVariable Long id, @RequestBody Inventory i) {
        try {
            Inventory stockActualizado = inventoryService.actualizarInventory(id, i);
            return ResponseEntity.ok(stockActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar registro de inventario", description = "Elimina físicamente el registro de stock del sistema utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = inventoryService.eliminarInventory(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}