package com.guild.events.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guild.events.domain.model.entity.Suscription;
import com.guild.events.resources.createResources.CreateSuscriptionResource;
import com.guild.events.resources.resources.SuscriptionResource;
import com.guild.shared.mapping.EnhancedModelMapper;

public class SuscriptionMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public SuscriptionResource toResource(Suscription model){
        return mapper.map(model, SuscriptionResource.class);
    }

    public Suscription toModel(CreateSuscriptionResource resource){
        return mapper.map(resource, Suscription.class);
    }

    public List<SuscriptionResource> modelListToResource(List<Suscription> modelList){
        return mapper.mapList(modelList, SuscriptionResource.class);
    }

}
