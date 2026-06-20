package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    public void probarGettersYSetters() {
        Inventory inv = new Inventory();
        inv.setId(1L);
        inv.setIdLibro(100L);
        inv.setCantidad(25);

        assertEquals(1L, inv.getId());
        assertEquals(100L, inv.getIdLibro());
        assertEquals(25, inv.getCantidad());
    }
}