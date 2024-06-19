package com.Royal.Main.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserAddress {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String address;

    @OneToOne
    private User user;


}
