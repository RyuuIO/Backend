package com.guild.events.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.guild.events.domain.model.entity.Happening;

public interface EventService {
    
    List<Happening> getAll();
    Happening getById(long id);
    Happening create(Happening request);
    Happening update(Long id, Happening request);
    ResponseEntity<?> delete(Long id);

}
