package com.todo.demo.controller;

import com.todo.demo.dto.NewTodoDTO;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class HomePageController {

    @Autowired
    private TodoItemService todoItemService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String openHomePage(Model model) {
        List<TodoItemEntity> todos = todoItemService.findAll();
        model.addAttribute("todos",todos);
        return "index";
    }
    @RequestMapping(value = "/todo",method = RequestMethod.POST)
    @ResponseBody
    public TodoItemEntity save(@RequestBody NewTodoDTO dto){ return todoItemService.save(dto); }

    @RequestMapping(value = "/todo/{todo-id}",method = RequestMethod.PUT)
    @ResponseBody
    public void save(@PathVariable("todo-id")long id){
        todoItemService.update(id);
    }

    @RequestMapping(value = "/todoDelay/{todo-id}",method = RequestMethod.PUT)
    @ResponseBody
    public void delay(@PathVariable("todo-id")long id){
        todoItemService.delay(id);
    }

    @RequestMapping(value = "/todoDelete/{todo-id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("todo-id") long id) {
        todoItemService.delete(id);
    }
}
