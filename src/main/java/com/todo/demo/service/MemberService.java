package com.todo.demo.service;

import com.todo.demo.entity.Member;
import com.todo.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member save(Member member){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));
        memberRepository.save(member);
        return member;
    }
    public void deleteMember(String username){
        Member member = memberRepository.findMemberByUsername(username);
        memberRepository.delete(member);
    }
}