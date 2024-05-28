package com.Royal.Main.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class RegistrationDTO {

        //@Email @NonNull @Size(min = 6, max = 30)
        private String email;

        //@NonNull @Size(min = 7, max = 20)
        //@Pattern(regexp = "^[A-Za-z0-9]+$", message = "First name must contain only letters and numbers")
        private String password;

        //@Size(min = 2, max = 20)
        //@Pattern(regexp = "^[A-Za-z0-9]+$", message = "First name must contain only letters and numbers")
        private String firstName;

        //@Size(min = 2, max = 20)
        //@Pattern(regexp = "^[A-Za-z0-9]+$", message = "Last name must contain only letters and numbers")
        private String lastName;

        //@NonNull
        @JsonFormat(pattern = "MM-dd-yyyy")
        private LocalDate dob;
}


