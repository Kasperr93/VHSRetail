package com.example.vhs.entity;

import com.example.vhs.entity.enums.MovieGenre;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MovieEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;
    private LocalDate releaseDate;
    private int stockAmount;
    private double rentPrice;
    private boolean isExclusive;
}