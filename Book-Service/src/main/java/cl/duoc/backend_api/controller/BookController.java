package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Book;
import cl.duoc.backend_api.service.BookService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService; // CAMBIADO: Ahora llamamos al Service, no al Repo

    @GetMapping
    public ResponseEntity<List<Book>> listar() {
        List<Book> libros = bookService.obtenerTodos();
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    public ResponseEntity<Book> crear(@RequestBody Book b) {
        
        Book libroCreado = bookService.guardarLibro(b);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(libroCreado);
    }
}