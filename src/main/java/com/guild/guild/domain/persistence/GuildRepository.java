package com.guild.guild.domain.persistence;


import com.guild.guild.domain.model.entity.Guild;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildRepository extends JpaRepository<Guild,Long> {
    List<Guild> findByGameId(Long genderId);
    List<Guild> findByNameContaining(String name);
}
