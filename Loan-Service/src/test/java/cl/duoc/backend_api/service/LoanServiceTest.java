package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Loan;
import cl.duoc.backend_api.repository.LoanRepository;
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
public class LoanServiceTest {

    @Mock
    private LoanRepository repository;

    @InjectMocks
    private LoanService loanService;

    @Test
    public void probarObtenerTodos() {
        List<Loan> listaMock = new ArrayList<>();
        listaMock.add(new Loan());
        when(repository.findAll()).thenReturn(listaMock);

        assertFalse(loanService.obtenerTodos().isEmpty());
    }

    @Test
    public void probarObtenerPorId() {
        Loan loan = new Loan();
        loan.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(loan));

        assertTrue(loanService.obtenerPorId(1L).isPresent());
    }

    @Test
    public void probarRegistrarPrestamo() {
        Loan loan = new Loan();
        // El test pasará directo por el catch de WebClient de forma segura sin romper el flujo de negocio
        when(repository.save(any(Loan.class))).thenReturn(loan);

        Loan resultado = loanService.registrarPrestamo(loan);
        assertNotNull(resultado);
    }

    @Test
    public void probarEliminarPrestamo() {
        when(repository.existsById(1L)).thenReturn(true);
        assertTrue(loanService.eliminarPrestamo(1L));
    }
}