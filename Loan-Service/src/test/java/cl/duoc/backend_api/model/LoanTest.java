package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    @Test
    public void probarGettersYSetters() {
        Loan loan = new Loan();
        LocalDate hoy = LocalDate.now();
        LocalDate entrega = hoy.plusDays(7);

        loan.setId(1L);
        loan.setIdLibro(101L);
        loan.setIdUsuario(202L);
        loan.setFechaPrestamo(hoy);
        loan.setFechaDevolucion(entrega);

        assertEquals(1L, loan.getId());
        assertEquals(101L, loan.getIdLibro());
        assertEquals(202L, loan.getIdUsuario());
        assertEquals(hoy, loan.getFechaPrestamo());
        assertEquals(entrega, loan.getFechaDevolucion());
    }
}