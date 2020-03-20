package com.example.vhs.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.vhs.converter.MovieMapper;
import com.example.vhs.dto.MovieData;
import com.example.vhs.entity.MovieEntity;
import com.example.vhs.service.MovieService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieData> findById(@PathVariable("id") Long id) {
        Optional<MovieEntity> findById = service.findById(id);

        return findById.map(movieEntity -> new ResponseEntity<>(MovieMapper.INSTANCE.movieEntityToMovieDto(movieEntity),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieData>> findAll() {
        List<MovieEntity> findAllMoviesEntity = service.findAll();

        if (findAllMoviesEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(convertAllMovies(findAllMoviesEntity), HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieEntity> save(@RequestBody MovieData movieData) {
        MovieEntity entity = MovieMapper.INSTANCE.movieDtoToMovieEntity(movieData);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        //TODO linkto - czy działa + id nie powinno sie dac z palca
        linkTo(methodOn(MovieController.class).save(movieData));

        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    private List<MovieData> convertAllMovies(List<MovieEntity> findAllMoviesEntity) {
        return findAllMoviesEntity.stream()
                .map(MovieMapper.INSTANCE::movieEntityToMovieDto)
                .collect(Collectors.toList());
    }
}