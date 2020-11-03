package com.example.rest_api.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

// adnotacja ORM Object Relationship Mapping
                 // Utworzona zostaje tableka MySQL o nazwie user i
@Entity          // kolumnach takich jak pola klasowe
@Data            // auto-generowanie getters/setters/toString
//@Getter          // auto-generowanie getters
//@Setter          // auto-generowanie Setters
//@ToString          // auto-generowanie metody toString()
public class User {
    @Id                                                 // klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto inkrementacja dot tylko tabelki
    private int userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime registrationDateTime = LocalDateTime.now();
    private boolean status = true;
}
