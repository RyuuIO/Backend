package com.guild.events.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guild.events.domain.model.entity.Suscription;

@Repository
public interface SuscriptionRepository extends JpaRepository<Suscription, Long> {
    //TODO: declare funtions for getByUserId and getByEventId
}
