package com.guild.guild.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guild.guild.domain.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
