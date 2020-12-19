package com.todo.demo.service;

import com.todo.demo.dto.NewTodoDTO;
import com.todo.demo.entity.Status;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemRepository todoItemRepository;

    //Database queires for get todos
    public List<TodoItemEntity> findAll(){
        return todoItemRepository.findAll();
    }
    public List<TodoItemEntity> findAllByOwner(String owner){
        return todoItemRepository.findAllByOwner(owner);
    }

    //Adding todos
    public TodoItemEntity save(NewTodoDTO dto){
        TodoItemEntity entity= new TodoItemEntity();
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setCreateDate(dto.getCreateDate());
        entity.setDate(dto.getDate());
        entity.setOwner(dto.getOwner());
        entity = todoItemRepository.save(entity);
        return entity;
    }
    public void update(long id) {
        TodoItemEntity entity = todoItemRepository.findById(id).get();
        entity.setStatus(Status.DONE);

        todoItemRepository.save(entity);
    }
    public void delay(long id){
        TodoItemEntity entity = todoItemRepository.findById(id).get();
        entity.setStatus(Status.DELAYED);
        entity.setDate(entity.getDate().plusDays(7));

        todoItemRepository.save(entity);
    }
    public void delete(long id){
        TodoItemEntity entity = todoItemRepository.findById(id).get();
        todoItemRepository.delete(entity);
    }
}
