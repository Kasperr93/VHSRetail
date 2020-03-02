package com.example.vhs.service;

import com.example.vhs.dao.MovieRepository;
import com.example.vhs.entity.MovieEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Optional<MovieEntity> findById(@RequestParam("id") Long id) {
        return repository.findById(id);
    }

    public List<MovieEntity> findAll() {
        return repository.findAll();
    }

    @Transactional
    public MovieEntity save(MovieEntity movieEntity) {
        return repository.save(movieEntity);
    }
}