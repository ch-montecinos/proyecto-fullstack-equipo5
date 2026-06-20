package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Book;
import cl.duoc.backend_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service 
public class BookService {

    @Autowired 
    private BookRepository repo;

    // 1. Listar todos
    public List<Book> obtenerTodos() {
        return repo.findAll();
    }

    // 2. Obtener un libro por ID
    public Optional<Book> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    // 3. Crear o guardar un libro
    public Book guardarLibro(Book b) {
        return repo.save(b);
    }

    // 4. Actualizar un libro existente
    public Book actualizarLibro(Long id, Book nuevoLibro) {
        return repo.findById(id).map(libroExistente -> {
            libroExistente.setTitulo(nuevoLibro.getTitulo());
            libroExistente.setAutor(nuevoLibro.getAutor());
            return repo.save(libroExistente);
        }).orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    // 5. Eliminar un libro
    public boolean eliminarLibro(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}