package com.guild.games.mapping;

import com.guild.games.domain.model.entity.Gender;
import com.guild.games.resource.createResource.CreateGenderResource;
import com.guild.games.resource.resource.GenderResource;
import com.guild.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class GenderMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public GenderResource toResource(Gender model){
        return mapper.map(model, GenderResource.class);
    }

    public Gender toModel(CreateGenderResource resource){
        return mapper.map(resource, Gender.class);
    }

    public List<GenderResource> modelListToResource(List<Gender> modelList){
        return mapper.mapList(modelList, GenderResource.class);
    }
}
