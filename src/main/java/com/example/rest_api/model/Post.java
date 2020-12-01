package com.example.rest_api.model;

import com.example.rest_api.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String title;
    @Type(type = "text")
    private String content;
    @Enumerated(value = EnumType.STRING)        // EnumType.ORDINAL - przechowuje indeks obiektów enum
                                                // EnumType.STRING  - przechowuje nazwy obiektów enum
    private Category category;
    private LocalDateTime publicationTime = LocalDateTime.now();
    @ManyToOne(
            fetch = FetchType.EAGER,            // FetchType.EAGER - zachłannny
                                                // FetchType.LAZY  - leniwy
            cascade = CascadeType.ALL           // CascadeType.ALL - determinuje kaskadę zapytań SQL związanych z powiązanymi rekordami
    )
    private User author;

}
