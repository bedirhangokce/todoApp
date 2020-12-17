package com.todo.demo.repository;

import com.todo.demo.entity.TodoItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItemEntity,Long> {
    List<TodoItemEntity> findAll();
}
