package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository repo;

    @Test
    public void probarGuardarYBuscar() {
        User u = new User();
        u.setId(1L);
        u.setNombre("Diego");
        
        when(repo.save(any(User.class))).thenReturn(u);
        when(repo.findById(1L)).thenReturn(Optional.of(u));

        User guardado = repo.save(u);
        assertNotNull(guardado);
        assertTrue(repo.findById(1L).isPresent());
    }

    @Test
    public void probarFindAll() {
        List<User> listaMock = new ArrayList<>();
        listaMock.add(new User());
        
        when(repo.findAll()).thenReturn(listaMock);
        assertFalse(repo.findAll().isEmpty());
    }
}