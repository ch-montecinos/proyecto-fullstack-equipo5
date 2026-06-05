package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Inventory;
import cl.duoc.backend_api.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Le avisa a Spring Boot que esta capa maneja las existencias y stock
public class InventoryService {

    @Autowired // Conectamos el repositorio de inventario
    private InventoryRepository repo;

    // Lógica para listar todo el inventario
    public List<Inventory> obtenerTodos() {
        return repo.findAll();
    }

    // Lógica para guardar o actualizar el stock de un libro
    public Inventory guardarInventory(Inventory i) {
        return repo.save(i);
    }
}