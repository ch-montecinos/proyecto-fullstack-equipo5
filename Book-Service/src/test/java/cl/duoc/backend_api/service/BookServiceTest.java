package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Book;
import cl.duoc.backend_api.repository.BookRepository;
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
public class BookServiceTest {

    @Mock
    private BookRepository repo;

    @InjectMocks
    private BookService bookService;

    @Test
    public void probarObtenerTodos() {
        List<Book> listaMock = new ArrayList<>();
        listaMock.add(new Book());
        when(repo.findAll()).thenReturn(listaMock);

        List<Book> resultado = bookService.obtenerTodos();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        verify(repo, times(1)).findAll();
    }

    @Test
    public void probarObtenerPorId() {
        Book b = new Book();
        b.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(b));

        Optional<Book> resultado = bookService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    public void probarGuardarLibro() {
        Book b = new Book();
        b.setTitulo("Java Avanzado");
        when(repo.save(b)).thenReturn(b);

        Book guardado = bookService.guardarLibro(b);
        assertNotNull(guardado);
        verify(repo, times(1)).save(b);
    }

    @Test
    public void probarEliminarLibroExitoso() {
        when(repo.existsById(1L)).thenReturn(true);
        doNothing().when(repo).deleteById(1L);

        boolean eliminado = bookService.eliminarLibro(1L);
        assertTrue(eliminado);
    }
}