package com.todo.demo.dto;

import com.todo.demo.entity.Status;

import java.time.LocalDate;

public class NewTodoDTO {

    private String description;
    private LocalDate date;
    private LocalDate createDate;
    private Status status;
    private Object owner;

    public String getOwner() {
        return owner.toString();
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
