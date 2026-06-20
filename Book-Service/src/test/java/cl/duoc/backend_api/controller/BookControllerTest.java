package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Book;
import cl.duoc.backend_api.service.BookService;
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
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void probarListar() {
        List<Book> librosMock = new ArrayList<>();
        librosMock.add(new Book());
        when(bookService.obtenerTodos()).thenReturn(librosMock);

        ResponseEntity<List<Book>> respuesta = bookController.listar();
        assertNotNull(respuesta);
        assertEquals(200, respuesta.getStatusCode().value());
        assertFalse(respuesta.getBody().isEmpty());
    }

    @Test
    public void probarObtenerPorIdEncontrado() {
        Book b = new Book();
        b.setId(1L);
        when(bookService.obtenerPorId(1L)).thenReturn(Optional.of(b));

        ResponseEntity<Book> respuesta = bookController.obtenerPorId(1L);
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals(1L, respuesta.getBody().getId());
    }

    @Test
    public void probarCrear() {
        Book b = new Book();
        when(bookService.guardarLibro(any(Book.class))).thenReturn(b);

        ResponseEntity<Book> respuesta = bookController.crear(b);
        assertEquals(201, respuesta.getStatusCode().value());
    }
}