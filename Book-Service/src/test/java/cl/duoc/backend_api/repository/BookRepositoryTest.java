package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Book;
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
public class BookRepositoryTest {

    @Mock
    private BookRepository repo;

    @Test
    public void probarGuardarYBuscar() {
        Book b = new Book();
        b.setId(1L);
        b.setTitulo("Spring Boot 3");
        b.setAutor("Duoc UC");
        
        when(repo.save(any(Book.class))).thenReturn(b);
        when(repo.findById(1L)).thenReturn(Optional.of(b));

        Book guardado = repo.save(b);
        assertNotNull(guardado);
        assertEquals("Spring Boot 3", guardado.getTitulo());

        Optional<Book> encontrado = repo.findById(1L);
        assertTrue(encontrado.isPresent());
    }

    @Test
    public void probarFindAll() {
        List<Book> listaMock = new ArrayList<>();
        listaMock.add(new Book());
        
        when(repo.findAll()).thenReturn(listaMock);

        List<Book> lista = repo.findAll();
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
    }
}