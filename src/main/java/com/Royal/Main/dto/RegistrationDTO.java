package com.Royal.Main.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class RegistrationDTO {

        @Email @NonNull @Size(min = 6, max = 30)
        private String email;

        @NonNull @Size(min = 7, max = 20)
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "First name must contain only letters and numbers")
        private String pass;

        @Size(min = 2, max = 20)
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "First name must contain only letters and numbers")
        private String firstName;

        @Size(min = 2, max = 20)
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Last name must contain only letters and numbers")
        private String lastName;

        @NonNull
        //in the future, we can enforce minimum age 18
        private LocalDateTime dob;
}


