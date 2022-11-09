package com.guild.games.domain.service;

import com.guild.games.domain.model.entity.Game;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GameService {
    List<Game> getAll();
    List<Game> getByGenderId(Long genderId);
    List<Game> getByName(String name);
    Game getById(Long id);
    Game create(Game request);
    Game update(Long id, Game request);
    ResponseEntity<?> delete(Long id);
}
