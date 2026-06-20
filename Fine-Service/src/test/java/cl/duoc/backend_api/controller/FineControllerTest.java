package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Fine;
import cl.duoc.backend_api.service.FineService;
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
public class FineControllerTest {

    @Mock
    private FineService fineService;

    @InjectMocks
    private FineController fineController;

    @Test
    public void probarGetAll() {
        List<Fine> lista = new ArrayList<>();
        lista.add(new Fine());
        when(fineService.obtenerTodas()).thenReturn(lista);

        ResponseEntity<List<Fine>> res = fineController.getAll();
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarGetById() {
        Fine fine = new Fine();
        when(fineService.obtenerPorId(1L)).thenReturn(Optional.of(fine));

        ResponseEntity<Fine> res = fineController.getById(1L);
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarCreate() {
        Fine fine = new Fine();
        when(fineService.guardarFine(any(Fine.class))).thenReturn(fine);

        ResponseEntity<Fine> res = fineController.create(fine);
        assertEquals(201, res.getStatusCode().value());
    }
}