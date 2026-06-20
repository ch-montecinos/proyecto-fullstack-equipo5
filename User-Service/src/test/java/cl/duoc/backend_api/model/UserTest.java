package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void probarGettersYSetters() {
        User user = new User();
        user.setId(1L);
        user.setNombre("Christian");
        user.setEmail("christian@duocuc.cl");
        user.setRol("ALUMNO");

        assertEquals(1L, user.getId());
        assertEquals("Christian", user.getNombre());
        assertEquals("christian@duocuc.cl", user.getEmail());
        assertEquals("ALUMNO", user.getRol());
    }
}