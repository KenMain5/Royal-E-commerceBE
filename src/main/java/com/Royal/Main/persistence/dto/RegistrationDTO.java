package com.Royal.Main.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class RegistrationDTO {

        @Email @NotBlank
        @Size(min = 6, max = 30)
        private String email;

        @NotBlank @Size(min = 7, max = 20)
        private String password;

        @NotBlank
        @Size(min = 2, max = 20)
        private String firstName;

        @NotBlank
        @Size(min = 2, max = 20)
        private String lastName;

        @NotBlank
        @JsonFormat(pattern = "MM-dd-yyyy")
        private LocalDate dob;
}


