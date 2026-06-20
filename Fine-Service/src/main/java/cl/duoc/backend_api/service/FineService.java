package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Fine;
import cl.duoc.backend_api.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FineService {

    @Autowired
    private FineRepository repository;

    // 1. Listar todas
    public List<Fine> obtenerTodas() {
        return repository.findAll();
    }

    // 2. Buscar por ID
    public Optional<Fine> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Guardar / Crear
    public Fine guardarFine(Fine fine) {
        return repository.save(fine);
    }

    // 4. Actualizar 
    public Fine actualizarFine(Long id, Fine nuevaFine) {
        return repository.findById(id).map(multaExistente -> {
            multaExistente.setIdUsuario(nuevaFine.getIdUsuario());
            multaExistente.setMonto(nuevaFine.getMonto());
            multaExistente.setMotivo(nuevaFine.getMotivo());
            multaExistente.setEstado(nuevaFine.getEstado());
            return repository.save(multaExistente);
        }).orElseThrow(() -> new RuntimeException("Multa no encontrada con ID: " + id));
    }

    // 5. Eliminar
    public boolean eliminarFine(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}