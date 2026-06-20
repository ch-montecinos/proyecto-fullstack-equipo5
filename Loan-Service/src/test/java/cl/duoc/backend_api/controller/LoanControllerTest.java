package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.Loan;
import cl.duoc.backend_api.service.LoanService;
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
public class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @Test
    public void probarListar() {
        List<Loan> lista = new ArrayList<>();
        lista.add(new Loan());
        when(loanService.obtenerTodos()).thenReturn(lista);

        ResponseEntity<List<Loan>> res = loanController.listar();
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarObtenerPorId() {
        Loan loan = new Loan();
        when(loanService.obtenerPorId(1L)).thenReturn(Optional.of(loan));

        ResponseEntity<Loan> res = loanController.obtenerPorId(1L);
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    public void probarCrear() {
        Loan loan = new Loan();
        when(loanService.registrarPrestamo(any(Loan.class))).thenReturn(loan);

        ResponseEntity<Loan> res = loanController.crear(loan);
        assertEquals(201, res.getStatusCode().value());
    }
}