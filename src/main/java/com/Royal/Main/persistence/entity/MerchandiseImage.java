package com.Royal.Main.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"merchandise"})
public class MerchandiseImage {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private byte[] imageBytes;

    @ManyToOne
    private Merchandise merchandise;
}
