package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Book;
import cl.duoc.backend_api.service.BookService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Tag(name = "Libros", description = "Operaciones de gestión y catálogo de libros de la biblioteca")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService; 

    @Operation(summary = "Listar todos los libros", description = "Retorna la lista completa de libros registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de libros obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Book>> listar() {
        List<Book> libros = bookService.obtenerTodos();
        return ResponseEntity.ok(libros);
    }

    @Operation(summary = "Obtener un libro por su ID", description = "Busca un libro específico en la base de datos usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Libro encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Book> obtenerPorId(@PathVariable Long id) {
        // Nota: Si tu método del servicio devuelve Optional, lo manejamos limpiamente aquí
        return bookService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo libro", description = "Crea un libro en el catálogo y retorna el objeto guardado con su ID generado.")
    @ApiResponse(responseCode = "201", description = "Libro creado exitosamente")
    @PostMapping
    public ResponseEntity<Book> crear(@RequestBody Book b) {
        Book libroCreado = bookService.guardarLibro(b);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroCreado);
    }

    @Operation(summary = "Actualizar un libro existente", description = "Modifica los datos de un libro existente en base a su ID.")
    @ApiResponse(responseCode = "200", description = "Libro actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado para actualizar")
    @PutMapping("/{id}")
    public ResponseEntity<Book> actualizar(@PathVariable Long id, @RequestBody Book b) {
        try {
            Book libroActualizado = bookService.actualizarLibro(id, b);
            return ResponseEntity.ok(libroActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un libro", description = "Elimina físicamente un libro de la base de datos usando su ID.")
    @ApiResponse(responseCode = "204", description = "Libro eliminado exitosamente (No Content)")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = bookService.eliminarLibro(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}