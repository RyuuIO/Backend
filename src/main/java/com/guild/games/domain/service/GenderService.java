package com.guild.games.domain.service;

import com.guild.games.domain.model.entity.Gender;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenderService {
    List<Gender> getAll();
    Gender getById(Long id);
    Gender create(Gender request);
    Gender update(Long id, Gender request);
    ResponseEntity<?> delete(Long id);
}
