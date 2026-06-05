package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Fine;
import cl.duoc.backend_api.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Le indica a Spring Boot que esta capa maneja el control de multas y penalizaciones
public class FineService {

    @Autowired // Conectamos el repositorio de multas
    private FineRepository repository;

    // Lógica para listar todas las multas existentes
    public List<Fine> obtenerTodas() {
        return repository.findAll();
    }

    // Lógica para crear una nueva multa
    public Fine guardarFine(Fine fine) {
        return repository.save(fine);
    }
}