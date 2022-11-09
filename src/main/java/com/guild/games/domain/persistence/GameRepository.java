package com.guild.games.domain.persistence;

import com.guild.games.domain.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByGenderId(Long genderId);
    List<Game> findByNameContaining(String name);
}
