package com.guild.events.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guild.events.domain.model.entity.Happening;
import com.guild.events.resources.createResources.CreateEventResource;
import com.guild.events.resources.resources.EventResource;
import com.guild.shared.mapping.EnhancedModelMapper;

public class EventMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public EventResource toResource(Happening model){
        return mapper.map(model, EventResource.class);
    }
    
    public Happening toModel(CreateEventResource resource){
        return mapper.map(resource, Happening.class);
    }

    public List<EventResource> modelListToResource(List<Happening> modelList){
        return mapper.mapList(modelList, EventResource.class);
    }

}
