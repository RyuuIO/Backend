package com.guild.events.resources.resources;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResource {
    private Long id;
    private LocalDate date;
    private LocalTime hour;
    private String title;
    private String description;
    private Long guildId;
}
