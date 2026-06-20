package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.User;
import cl.duoc.backend_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService userService;

    @Test
    public void probarObtenerTodos() {
        List<User> listaMock = new ArrayList<>();
        listaMock.add(new User());
        when(repo.findAll()).thenReturn(listaMock);

        assertFalse(userService.obtenerTodos().isEmpty());
    }

    @Test
    public void probarObtenerPorId() {
        User u = new User();
        u.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(u));

        assertTrue(userService.obtenerPorId(1L).isPresent());
    }

    @Test
    public void probarGuardarUsuario() {
        User u = new User();
        when(repo.save(u)).thenReturn(u);

        assertNotNull(userService.guardarUsuario(u));
    }

    @Test
    public void probarEliminarUsuario() {
        when(repo.existsById(1L)).thenReturn(true);
        
        assertTrue(userService.eliminarUsuario(1L));
    }
}