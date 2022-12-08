package com.guild.guild.domain.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.guild.guild.domain.model.entity.Rol;

public interface RolService {
    
    List<Rol> getAll();
    Rol getById(long id);
    Rol create(Rol request);
    Rol update(Long id, Rol request);
    ResponseEntity<?> delete(Long id);
}
