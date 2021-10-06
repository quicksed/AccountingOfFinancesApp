package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
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

    private Date birthDate;

    @CreationTimestamp
    private Date registrationDate;

    public User() {

    }

    public User(String name, String surname, String email, String password, Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
}
