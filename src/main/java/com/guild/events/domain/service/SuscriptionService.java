package com.guild.events.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.guild.events.domain.model.entity.Suscription;

public interface SuscriptionService {

    List<Suscription> getAll();
    Suscription getById(Long id);
    Suscription create(Suscription request);
    ResponseEntity<?> delete( Long id );

    //TODO: implement function getByUserId and getByEventId

}
