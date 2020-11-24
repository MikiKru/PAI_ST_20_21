package com.example.rest_api.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// adnotacja ORM Object Relationship Mapping
                 // Utworzona zostaje tableka MySQL o nazwie user i
@Entity          // kolumnach takich jak pola klasowe
@Data            // auto-generowanie getters/setters/toString
@AllArgsConstructor // auto-generacja konstruktora z wszystkimi polami w argymencie
@NoArgsConstructor  // auto-generacja kontruktora bezargumentowego
//@Getter          // auto-generowanie getters
//@Setter          // auto-generowanie Setters
//@ToString          // auto-generowanie metody toString()
@Table(name = "users", indexes = @Index(name = "rdt", columnList = "registration_time"))
public class User {
    @Id                                                 // klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto inkrementacja dot tylko tabelki
    private int userId;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "registration_time")                 // edycja właściwości kolumny
    private LocalDateTime registrationDateTime;
    @Type(type = "text")
    private String description;
    private boolean status;
//    @Transient                                          // wykluczenie z mapowania ORM
//    private String secretCode;
    @ManyToMany(fetch = FetchType.EAGER)                  // N : M
    @JoinTable(                                           // adnotacja tworzy tabelkę relacji
            name = "users_to_roles",                            // nazwa tabeli
            joinColumns = @JoinColumn(name = "user_id"),        // FK z tab users
            inverseJoinColumns = @JoinColumn(name = "role_id")  // FK z tab roles
    )
    private Set<Role> roles = new HashSet<>();


    public User(String name, String lastName, String email, String password, LocalDateTime registrationDateTime, String description, boolean status) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationDateTime = registrationDateTime;
        this.description = description;
        this.status = status;
    }
}
