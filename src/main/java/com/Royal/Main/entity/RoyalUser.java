package com.Royal.Main.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class RoyalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDateTime dob;

    private Boolean isVerified;

    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "royalUser")
    private List<RoyalUserFinancials> royalUserFinancials;

    public RoyalUser() {
    }

    public RoyalUser(String email, String password, String firstName, String lastName, LocalDateTime dob, Boolean isVerified, LocalDateTime registrationDate, List<RoyalUserFinancials> royalUserFinancials) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.isVerified = isVerified;
        this.registrationDate = registrationDate;
        this.royalUserFinancials = royalUserFinancials;
    }
}
