package com.example.vhs.dto;

import com.example.vhs.entity.enums.MovieGenre;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieData extends RepresentationModel<MovieData> {

    private Long id;

    @NotBlank(message = "Title field cannot be empty")
    private String title;

    private MovieGenre movieGenre;

    @NotNull(message = "Release date field cannot be null")
    private LocalDate releaseDate;

    @NotEmpty(message = "The stock amount field cannot be empty")
    @PositiveOrZero(message = "Stock amount field cannot has negative value")
    private Integer stockAmount;

    @Positive
    @NotNull(message = "The rental price field cannot be null")
    private Double rentPrice;

    @NotNull(message = "The field cannot be empty")
    private Boolean isExclusive;
}