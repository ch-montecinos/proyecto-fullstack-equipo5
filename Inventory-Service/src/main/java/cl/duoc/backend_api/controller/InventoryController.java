package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Inventory;
import cl.duoc.backend_api.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository repo;

    @GetMapping
    public List<Inventory> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Inventory guardar(@RequestBody Inventory i) {
        return repo.save(i);
    }
}