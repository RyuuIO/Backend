package com.guild.guild.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guild.guild.domain.model.entity.Guild;
import com.guild.guild.resources.createdResource.CreatedGuildResource;
import com.guild.guild.resources.resources.GuildResource;
import com.guild.shared.mapping.EnhancedModelMapper;

public class GuildMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public GuildResource toResource(Guild model){
        return mapper.map(model, GuildResource.class);
    }

    public Guild toModel(CreatedGuildResource resource){
        return mapper.map(resource, Guild.class);
    }

    public List<GuildResource> modelListToResource(List<Guild> modelList){
        return mapper.mapList(modelList, GuildResource.class);
    }
    
}
