package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookModelTest {

    @Test
    public void probarGettersYSetters() {
        Book book = new Book();
        book.setId(1L);
        book.setTitulo("El Aleph");
        book.setAutor("Jorge Luis Borges");

        assertEquals(1L, book.getId());
        assertEquals("El Aleph", book.getTitulo());
        assertEquals("Jorge Luis Borges", book.getAutor());
    }
}