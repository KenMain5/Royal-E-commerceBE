package com.Royal.Main.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "merchandises")
@EqualsAndHashCode
@Builder
public class Merchant {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Boolean isVerified;

    @NotNull
    private Boolean isAccountLocked;

    @NotNull
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Merchandise> merchandises;

    @ManyToOne
    private Role role;
}
