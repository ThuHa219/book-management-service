package com.example.bookmanagement.model;

import java.util.Optional;

public class BookPartialDTO {
    private Optional<Integer> id;

    private Optional<Integer> userId;
    private Optional<String> name;
    private Optional<String> description;

    public BookPartialDTO() {
        // do nothing
    }

    public void setId(int id) {
        this.id = Optional.ofNullable(id);
    }

    public Optional<Integer> getId() {
        return this.id;
    }

    public void setUserId(int userId) {
        this.userId = Optional.ofNullable(userId);
    }

    public Optional<Integer> getUserId() {
        return this.userId;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public Optional<String> getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    public Optional<String> getDescription() {
        return this.description;
    }

}
