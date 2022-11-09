package com.guild.games.mapping;

import com.guild.games.domain.model.entity.Game;
import com.guild.games.resource.createResource.CreateGameResource;
import com.guild.games.resource.resource.GameResource;
import com.guild.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class GameMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public GameResource toResource(Game model){
        return mapper.map(model, GameResource.class);
    }

    public Game toModel(CreateGameResource resource){
        return mapper.map(resource, Game.class);
    }

    public List<GameResource> modelListToResource(List<Game> modelList){
        return mapper.mapList(modelList, GameResource.class);
    }
}
