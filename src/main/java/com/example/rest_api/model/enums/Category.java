package com.example.rest_api.model.enums;

import lombok.Getter;

@Getter
public enum Category {
    IT("IT"),
    CODING("PROGRAMOWANIE"),
    TESTING("TESTOWANIE OPROGRAMOWANIA"),
    DEV_OPS("ADMINISTRACJA");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
