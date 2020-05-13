package com.example.vhs.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserData extends RepresentationModel<UserData> {

    private Long id;

    @NotBlank(message = "First name field cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name field cannot be empty")
    private String lastName;

    @NotNull(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Email field cannot be empty")
    private String email;
    private Double walletBalance;
}