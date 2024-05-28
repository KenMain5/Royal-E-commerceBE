package com.Royal.Main.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@ToString(exclude = {"userFinancials", "roles", "user"})
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;


    private LocalDate dob;

    private Boolean isVerified;

    private Boolean isAccountLocked;

    private LocalDate registrationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFinancial> userFinancials;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="user_roles",
            joinColumns= @JoinColumn(name= "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<PurchaseOrder> purchaseOrders;

    @OneToOne
    private UserAddress userAddress;
}
