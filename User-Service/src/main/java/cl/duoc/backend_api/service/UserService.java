package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.User;
import cl.duoc.backend_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Le indica a Spring Boot que esta es la capa de lógica de negocio
public class UserService {

    @Autowired // Conectamos el repositorio de usuarios
    private UserRepository repo;

    // Lógica para listar todos los usuarios/alumnos
    public List<User> obtenerTodos() {
        return repo.findAll();
    }

    // Lógica para registrar un usuario nuevo
    public User guardarUsuario(User u) {
        return repo.save(u);
    }
}