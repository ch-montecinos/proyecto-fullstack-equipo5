package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Search;
import cl.duoc.backend_api.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public List<Search> findAll() {
        return searchRepository.findAll();
    }

    public Search save(Search search) {
        return searchRepository.save(search);
    }
}