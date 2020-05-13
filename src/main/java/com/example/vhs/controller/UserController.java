package com.example.vhs.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.example.vhs.converter.UserMapper;
import com.example.vhs.dto.UserData;
import com.example.vhs.entity.UserEntity;
import com.example.vhs.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(final UserService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserData> findById(@PathVariable("id") final Long id) {
        Optional<UserEntity> findById = service.findById(id);

        return findById.map(userEntity -> new ResponseEntity<>(UserMapper.INSTANCE.userEntityToUserDto(userEntity),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserData>> findAll() {
        List<UserEntity> findAllUsersEntity = service.findAll();

        if (findAllUsersEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(convertAllUsers(findAllUsersEntity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserData> save(@RequestBody final UserData userData) {

        if (userData == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        UserEntity entity = UserMapper.INSTANCE.userDtoToUserEntity(userData);
        UserEntity userEntitySaved = service.save(entity);
        UserData userDataSaved = UserMapper.INSTANCE.userEntityToUserDto(userEntitySaved);
        userDataSaved.add(createRelLink(userEntitySaved));

        return new ResponseEntity<>(userDataSaved, HttpStatus.CREATED);
    }

    private List<UserData> convertAllUsers(List<UserEntity> findAllUsersEntity) {
        return findAllUsersEntity.stream()
                .map(UserMapper.INSTANCE::userEntityToUserDto)
                .collect(Collectors.toList());
    }

    private Link createRelLink(UserEntity userEntity) {
        return linkTo(UserController.class).slash(userEntity.getId()).withSelfRel();
    }
}