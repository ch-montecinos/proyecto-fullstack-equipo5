package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FineTest {

    @Test
    public void probarGettersYSetters() {
        Fine fine = new Fine();
        fine.setId(1L);
        fine.setIdUsuario(10L);
        fine.setMonto(5000.0);
        fine.setMotivo("Retraso de entrega");
        fine.setEstado("PENDIENTE");

        assertEquals(1L, fine.getId());
        assertEquals(10L, fine.getIdUsuario());
        assertEquals(5000.0, fine.getMonto());
        assertEquals("Retraso de entrega", fine.getMotivo());
        assertEquals("PENDIENTE", fine.getEstado());
    }
}