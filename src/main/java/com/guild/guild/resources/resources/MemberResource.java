package com.guild.guild.resources.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResource {
    private Long id;
    private Long playerId;
    private Long guildId;
    private Long rolId;
}
