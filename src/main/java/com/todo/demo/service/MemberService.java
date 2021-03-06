package com.todo.demo.service;

import com.todo.demo.entity.Member;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.repository.MemberRepository;
import com.todo.demo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TodoItemRepository todoItemRepository;
    @Autowired
    TodoItemService itemService;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member save(Member dto){
        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setRole(dto.getRole());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(dto.getPassword()));
        memberRepository.save(member);
        return member;
    }
    public void changeRole(String username){
        Member member = memberRepository.findMemberByUsername(username);
        if (member.getRole().equals("USER")){
            member.setRole("ADMIN");
        }
        else member.setRole("USER");
        memberRepository.save(member);
    }

    public void deleteMember(String username){
        List<TodoItemEntity> itemList = todoItemRepository.findAllByOwner(username);
        for (TodoItemEntity a: itemList) {
            itemService.delete(a.getId());
        }
        Member member = memberRepository.findMemberByUsername(username);
        memberRepository.delete(member);
    }
}