package com.Royal.Main.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString(exclude = {"users"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String givenRole;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @OneToOne
    private Merchant merchant;
}
