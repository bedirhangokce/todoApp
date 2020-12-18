package com.todo.demo.controller;

import com.todo.demo.dto.NewTodoDTO;
import com.todo.demo.entity.Member;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.service.MemberService;
import com.todo.demo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private TodoItemService todoItemService;
    @Autowired
    private MemberService memberService;
    @RequestMapping("/")
    public ModelAndView defaultHome() {
        return new ModelAndView("home");
    }
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String openTodosPage(Model model){
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        System.out.println(principal.getAuthorities().toString().contains("ROLE_ADMIN"));
        if (principal.getAuthorities().toString().contains("ROLE_ADMIN")){
            List<TodoItemEntity> todos = todoItemService.findAll();
            List<Member> users = memberService.findAll();
            model.addAttribute("todos",todos);
            model.addAttribute("users",users);
        }else {
            List<TodoItemEntity> todos = todoItemService.findAllByOwner(username);
            List<Member> users = memberService.findAll();
            model.addAttribute("todos",todos);
            model.addAttribute("users",users);
        }
        //Username
        System.out.println(username);
        //List of roles
        System.out.println(principal.getAuthorities());
        //Session ID
        System.out.println(principal.getDetails());
        return "index";
    }
    @RequestMapping(value = "/addTodo",method = RequestMethod.POST)
    @ResponseBody
    public TodoItemEntity save(@RequestBody NewTodoDTO dto){ return todoItemService.save(dto); }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Member save(@RequestBody Member member){ return memberService.save(member); }

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

    @RequestMapping(value = "/userDelete/{username}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable("username") String username) {
        memberService.deleteMember(username);
    }
}
