package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Inventory;
import cl.duoc.backend_api.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    @Test
    public void probarListar() {
        List<Inventory> lista = new ArrayList<>();
        lista.add(new Inventory());
        when(inventoryService.obtenerTodos()).thenReturn(lista);

        ResponseEntity<List<Inventory>> res = inventoryController.listar();
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarObtenerPorId() {
        Inventory inv = new Inventory();
        when(inventoryService.obtenerPorId(1L)).thenReturn(Optional.of(inv));

        ResponseEntity<Inventory> res = inventoryController.obtenerPorId(1L);
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarGuardar() {
        Inventory inv = new Inventory();
        when(inventoryService.guardarInventory(any(Inventory.class))).thenReturn(inv);

        ResponseEntity<Inventory> res = inventoryController.guardar(inv);
        assertEquals(201, res.getStatusCode().value());
    }
}