package com.example.vhs.dto;

import com.example.vhs.entity.enums.MovieGenre;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class MovieData {

    private Long id;

    @NotBlank(message = "Title field cannot be empty")
    private String title;

    //TODO validation
    private MovieGenre movieGenre;

    @NotNull(message = "Release date field cannot be null")
    private LocalDate releaseDate;

    @NotEmpty(message = "The stock amount field cannot be empty")
    @PositiveOrZero(message = "Stock amount field cannot has negative value")
    private int stockAmount;

    @Positive
    @NotNull(message = "The rental price field cannot be null")
    private double rentPrice;

    @NotNull(message = "The field cannot be empty")
    private boolean isExclusive;
}