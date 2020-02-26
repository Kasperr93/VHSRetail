package com.example.vhs.converter;

import com.example.vhs.dto.MovieData;
import com.example.vhs.entity.MovieEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

    MovieData movieEntityToMovieDto(MovieEntity movieEntity);

    MovieEntity movieDtoToMovieEntity(MovieData movieData);
}