package com.guild.guild.domain.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.guild.guild.domain.model.entity.Member;

public interface MemberService {
    
    List<Member> getAll();
    Member getById(long id);
    Member create(Member request);
    Member update(Long id, Member request);
    ResponseEntity<?> delete(long id);

}
