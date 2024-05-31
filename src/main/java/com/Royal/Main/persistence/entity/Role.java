package com.Royal.Main.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@ToString(exclude = {"users", "merchants"}) @EqualsAndHashCode
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String givenRole;

    @OneToMany
    private List<User> users;

    @OneToMany(mappedBy = "role")
    private List<Merchant> merchants;
}
