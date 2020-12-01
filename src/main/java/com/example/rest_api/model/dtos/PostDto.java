package com.example.rest_api.model.dtos;

import com.example.rest_api.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

// Klasa do transferu warości pomiędzy warstwą fornt-end i back-end
// Klasa wspierająca adnotacje dotyczące walidacji
@AllArgsConstructor
@Data
public class PostDto {
    private String title;
    private String content;
    private Category category;
    private int authorId;
}
