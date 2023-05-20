package com.vishwa.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Schema(
        description = "Description about USER DTO"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDto {

    // Hibernate Validator API
    private Long id;

    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "First Name should not be empty or null")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "Last Name should not be empty or null")
    private String lastName;

    @Schema(
            description = "User Email"
    )
    @NotEmpty(message = "Email should not be empty or null")
    @Email
    private String emailAddress;

}
