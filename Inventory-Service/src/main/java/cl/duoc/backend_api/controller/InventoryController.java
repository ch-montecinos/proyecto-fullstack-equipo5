package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Inventory;
import cl.duoc.backend_api.service.InventoryService; // IMPORTAMOS EL NUEVO SERVICE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService; // CAMBIADO: Apuntamos al Service

    @GetMapping
    public ResponseEntity<List<Inventory>> listar() {
        // Solicitamos la lista al Service y devolvemos 200 OK
        List<Inventory> stock = inventoryService.obtenerTodos();
        return ResponseEntity.ok(stock);
    }

    @PostMapping
    public ResponseEntity<Inventory> guardar(@RequestBody Inventory i) {
        // Delegamos la persistencia a la capa de negocio
        Inventory stockGuardado = inventoryService.guardarInventory(i);
        
        // Devolvemos el estatus 201 Created reglamentario para inserciones
        return ResponseEntity.status(HttpStatus.CREATED).body(stockGuardado);
    }
}