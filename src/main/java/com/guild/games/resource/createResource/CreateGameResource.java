package com.guild.games.resource.createResource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateGameResource {
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotBlank
    @NotBlank
    private String image;

    @NotNull
    private Long genderId;
}
