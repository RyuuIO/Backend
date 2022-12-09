package com.guild.events.resources.createResources;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSuscriptionResource {

    Long id;

    @NotNull
    Long playerId;

    @NotNull
    Long eventId;

}
