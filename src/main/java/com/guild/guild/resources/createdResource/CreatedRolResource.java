package com.guild.guild.resources.createdResource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedRolResource {

    Long id;

    @NotNull
    @NotBlank
    String name;
}
