package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Inventory;
import cl.duoc.backend_api.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository repo;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    public void probarObtenerTodos() {
        List<Inventory> listaMock = new ArrayList<>();
        listaMock.add(new Inventory());
        when(repo.findAll()).thenReturn(listaMock);

        assertFalse(inventoryService.obtenerTodos().isEmpty());
    }

    @Test
    public void probarObtenerPorId() {
        Inventory inv = new Inventory();
        inv.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(inv));

        assertTrue(inventoryService.obtenerPorId(1L).isPresent());
    }

    @Test
    public void probarGuardarInventory() {
        Inventory inv = new Inventory();
        when(repo.save(inv)).thenReturn(inv);

        assertNotNull(inventoryService.guardarInventory(inv));
    }

    @Test
    public void probarEliminarInventory() {
        when(repo.existsById(1L)).thenReturn(true);
        
        assertTrue(inventoryService.eliminarInventory(1L));
    }
}