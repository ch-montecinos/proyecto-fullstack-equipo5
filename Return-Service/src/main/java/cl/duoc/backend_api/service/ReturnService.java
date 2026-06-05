package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Return;
import cl.duoc.backend_api.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    public List<Return> findAll() {
        return returnRepository.findAll();
    }

    public Return save(Return returnEntity) {
        return returnRepository.save(returnEntity);
    }
}