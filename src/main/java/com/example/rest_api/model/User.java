package com.example.rest_api.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

// adnotacja ORM Object Relationship Mapping
                 // Utworzona zostaje tableka MySQL o nazwie user i
@Entity          // kolumnach takich jak pola klasowe
@Data            // auto-generowanie getters/setters/toString
@AllArgsConstructor // auto-generacja konstruktora z wszystkimi polami w argymencie
@NoArgsConstructor  // auto-generacja kontruktora bezargumentowego
//@Getter          // auto-generowanie getters
//@Setter          // auto-generowanie Setters
//@ToString          // auto-generowanie metody toString()
@Table(name = "users")
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
    private LocalDateTime registrationDateTime = LocalDateTime.now();
    @Type(type = "text")
    private String description;
    private boolean status = true;
//    @Transient                                          // wykluczenie z mapowania ORM
//    private String secretCode;
}
