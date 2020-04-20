package com.example.vhs.converter;

import com.example.vhs.dto.MovieData;
import com.example.vhs.entity.MovieEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieData movieEntityToMovieDto(MovieEntity movieEntity);

    MovieEntity movieDtoToMovieEntity(MovieData movieData);
}