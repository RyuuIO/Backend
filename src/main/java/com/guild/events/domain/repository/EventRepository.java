package com.guild.events.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guild.events.domain.model.entity.Happening;

@Repository
public interface EventRepository extends JpaRepository<Happening, Long> {
    
}
