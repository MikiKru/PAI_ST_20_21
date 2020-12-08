package com.example.rest_api.model.dtos;

import com.example.rest_api.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Klasa do transferu warości pomiędzy warstwą fornt-end i back-end
// Klasa wspierająca adnotacje dotyczące walidacji
@AllArgsConstructor
@Data
public class PostDto {
//    @NotNull      // sprawdza czy wartość przekazywanego obiektu nie jest null
//    @NotEmpty     // sprawdza czy wartośc przekazywanego obiektu nie jest ""
//    @NotBlank     // kombinacja dwóch powyższych adnotacji
    @NotBlank(message = "Title must be not null")
    @Size(min = 5, max = 255, message = "Title must contain at least {min} to {max} characters")
    private String title;
    @NotBlank(message = "Content must be not null")
    @Size(min = 255, message = "Content must contain at least {min} characters")
    private String content;
//    @NotBlank
    private Category category;
//    @NotBlank(message = "Author id must be not null")
    private int authorId;
}
