package com.guild.events.resources.createResources;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventResource {
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime hour;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Long guildId;
}
