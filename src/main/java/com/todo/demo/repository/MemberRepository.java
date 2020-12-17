package com.todo.demo.repository;

import com.todo.demo.entity.Member;
import com.todo.demo.entity.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findAll();
    Member findMemberByUsername(String username);
}
