package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Book;
import cl.duoc.backend_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service 
public class BookService {

    @Autowired 
    private BookRepository repo;

    
    public List<Book> obtenerTodos() {
        return repo.findAll();
    }

    
    public Book guardarLibro(Book b) {
        return repo.save(b);
    }
}