package com.example.vhs.service;

import com.example.vhs.dao.UserRepository;
import com.example.vhs.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<UserEntity> findById(@RequestParam("id") Long id) {
        return repository.findById(id);
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return repository.save(userEntity);
    }
}