package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Inventory;
import cl.duoc.backend_api.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repo;

    // 1. Listar todos
    public List<Inventory> obtenerTodos() {
        return repo.findAll();
    }

    // 2. Buscar por ID
    public Optional<Inventory> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    // 3. Guardar / Crear
    public Inventory guardarInventory(Inventory i) {
        return repo.save(i);
    }

    // 4. Actualizar 
    public Inventory actualizarInventory(Long id, Inventory nuevoInventory) {
        return repo.findById(id).map(stockExistente -> {
            stockExistente.setIdLibro(nuevoInventory.getIdLibro());
            stockExistente.setCantidad(nuevoInventory.getCantidad());
            return repo.save(stockExistente);
        }).orElseThrow(() -> new RuntimeException("Registro de inventario no encontrado con ID: " + id));
    }

    // 5. Eliminar
    public boolean eliminarInventory(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}