package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Return;
import cl.duoc.backend_api.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    // 1. Listar todos
    public List<Return> findAll() {
        return returnRepository.findAll();
    }

    // 2. Buscar por ID
    public Optional<Return> findById(Long id) {
        return returnRepository.findById(id);
    }

    // 3. Guardar / Crear
    public Return save(Return returnEntity) {
        return returnRepository.save(returnEntity);
    }

    // 4. Actualizar 
    public Return update(Long id, Return nuevoReturn) {
        return returnRepository.findById(id).map(devExistente -> {
            devExistente.setIdPrestamo(nuevoReturn.getIdPrestamo());
            devExistente.setFechaDevolucion(nuevoReturn.getFechaDevolucion());
            devExistente.setEstadoLibro(nuevoReturn.getEstadoLibro());
            return returnRepository.save(devExistente);
        }).orElseThrow(() -> new RuntimeException("Registro de devolución no encontrado con ID: " + id));
    }

    // 5. Eliminar
    public boolean delete(Long id) {
        if (returnRepository.existsById(id)) {
            return returnRepository.deleteById(id);
            return true;
        }
        return false;
    }
}