package com.todo.demo.repository;

import com.todo.demo.entity.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItemEntity,Long> {
    List<TodoItemEntity> findAll();
    List<TodoItemEntity> findAllByOwner(String owner);
}
