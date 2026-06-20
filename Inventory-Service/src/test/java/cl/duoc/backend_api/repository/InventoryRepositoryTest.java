package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryRepositoryTest {

    @Mock
    private InventoryRepository repo;

    @Test
    public void probarGuardarYBuscar() {
        Inventory inv = new Inventory();
        inv.setId(1L);
        inv.setIdLibro(50L);
        
        when(repo.save(any(Inventory.class))).thenReturn(inv);
        when(repo.findById(1L)).thenReturn(Optional.of(inv));

        Inventory guardado = repo.save(inv);
        assertNotNull(guardado);
        assertTrue(repo.findById(1L).isPresent());
    }

    @Test
    public void probarFindAll() {
        List<Inventory> listaMock = new ArrayList<>();
        listaMock.add(new Inventory());
        
        when(repo.findAll()).thenReturn(listaMock);
        assertFalse(repo.findAll().isEmpty());
    }
}