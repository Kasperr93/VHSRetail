package com.example.vhs.converter;

import com.example.vhs.dto.MovieData;
import com.example.vhs.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieData movieEntityToMovieDto(MovieEntity movieEntity);

    MovieEntity movieDtoToMovieEntity(MovieData movieData);
}