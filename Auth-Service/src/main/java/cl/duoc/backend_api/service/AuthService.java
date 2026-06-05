package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Auth;
import cl.duoc.backend_api.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public List<Auth> findAll() {
        return authRepository.findAll();
    }

    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }
}