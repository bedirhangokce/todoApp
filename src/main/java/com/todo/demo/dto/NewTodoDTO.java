package com.todo.demo.dto;

import com.todo.demo.entity.Status;
import lombok.Data;
import java.time.LocalDate;

//Lombok usage for readable code
public @Data class NewTodoDTO {

    private String description;
    private LocalDate date;
    private LocalDate createDate;
    private Status status;
    private Object owner;

    //Return name of owner as a string
    public String getOwner() {
        return owner.toString();
    }
}
