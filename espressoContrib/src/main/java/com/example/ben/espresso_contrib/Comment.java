package com.example.ben.espresso_contrib;

/**
 * A simple POJO representing a comment
 */
public class Comment {

    public String getName() {
        return name;
    }

    private final String name;

    public String getDescription() {
        return description;
    }

    private final String description;

    public Comment(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
