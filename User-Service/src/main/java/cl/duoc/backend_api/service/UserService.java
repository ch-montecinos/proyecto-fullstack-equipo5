package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.User;
import cl.duoc.backend_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // 1. Listar todos
    public List<User> obtenerTodos() {
        return repo.findAll();
    }

    // 2. Buscar por ID
    public Optional<User> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    // 3. Guardar / Crear
    public User guardarUsuario(User u) {
        return repo.save(u);
    }

    // 4. Actualizar 
    public User actualizarUsuario(Long id, User nuevoUsuario) {
        return repo.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNombre(nuevoUsuario.getNombre());
            usuarioExistente.setEmail(nuevoUsuario.getEmail());
            usuarioExistente.setRol(nuevoUsuario.getRol());
            return repo.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // 5. Eliminar
    public boolean eliminarUsuario(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}