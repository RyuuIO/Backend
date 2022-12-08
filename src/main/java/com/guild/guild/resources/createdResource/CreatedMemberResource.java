package com.guild.guild.resources.createdResource;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedMemberResource {
    
    private Long id;
    
    @NotNull
    private Long playerId;

    @NotNull
    private Long guildId;

    @NotNull
    private Long rolId;

}
