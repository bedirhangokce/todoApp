package com.todo.demo.entity;


import com.sun.org.glassfish.gmbal.ManagedObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class TodoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private TodoUserEntity user;

    public TodoUserEntity getUser() {
        return user;
    }

    public void setUser(TodoUserEntity user) {
        this.user = user;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
