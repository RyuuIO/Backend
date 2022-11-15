package com.guild.guild.domain.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.guild.guild.domain.model.entity.Guild;

public interface GuildService {

    List<Guild> getAll();
    List<Guild> getByGameId(Long gameId);
    List<Guild> getByName(String name);
    Guild getById(Long id);
    Guild create(Guild request);
    Guild update(Long id, Guild request);
    ResponseEntity<?> delete(Long id);
    
}
