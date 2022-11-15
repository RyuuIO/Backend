package com.guild.guild.resources.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuildResource {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Long gameId;
}
