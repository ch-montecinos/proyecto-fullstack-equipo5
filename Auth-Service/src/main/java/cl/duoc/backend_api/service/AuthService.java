package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Auth;
import cl.duoc.backend_api.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    // 1. Listar todos
    public List<Auth> findAll() {
        return authRepository.findAll();
    }

    // 2. Buscar por ID
    public Optional<Auth> findById(Long id) {
        return authRepository.findById(id);
    }

    // 3. Guardar / Registrar
    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }

    // 4. Actualizar 
    public Auth actualizar(Long id, Auth nuevoAuth) {
        return authRepository.findById(id).map(authExistente -> {
            authExistente.setUsername(nuevoAuth.getUsername());
            authExistente.setPassword(nuevoAuth.getPassword());
            authExistente.setIdUsuario(nuevoAuth.getIdUsuario());
            return authRepository.save(authExistente);
        }).orElseThrow(() -> new RuntimeException("Registro de autenticación no encontrado con ID: " + id));
    }

    // 5. Eliminar
    public boolean eliminar(Long id) {
        if (authRepository.existsById(id)) {
            authRepository.deleteById(id);
            return true;
        }
        return false;
    }
}