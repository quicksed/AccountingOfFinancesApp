package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "User.roles",
                attributeNodes = {
                        @NamedAttributeNode(value = "roles")
                }
        )
})
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq_generator",
            sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq_generator")
    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Instant birthDate;

    @CreationTimestamp
    private Instant registrationDate;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;

    @OneToMany(mappedBy = "user")
    private Set<Category> categories;

    @OneToMany(mappedBy = "user")
    private Set<Currency> currencies;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    public User() {

    }

    public User(String name, String surname, String email, String password, Instant birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
}
