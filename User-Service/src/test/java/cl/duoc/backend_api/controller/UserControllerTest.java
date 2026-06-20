package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.User;
import cl.duoc.backend_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void probarListar() {
        List<User> lista = new ArrayList<>();
        lista.add(new User());
        when(userService.obtenerTodos()).thenReturn(lista);

        ResponseEntity<List<User>> res = userController.listar();
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarObtenerPorId() {
        User u = new User();
        when(userService.obtenerPorId(1L)).thenReturn(Optional.of(u));

        ResponseEntity<User> res = userController.obtenerPorId(1L);
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarCrear() {
        User u = new User();
        when(userService.guardarUsuario(any(User.class))).thenReturn(u);

        ResponseEntity<User> res = userController.crear(u);
        assertEquals(201, res.getStatusCode().value());
    }
}