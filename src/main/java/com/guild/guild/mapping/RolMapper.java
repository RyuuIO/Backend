package com.guild.guild.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guild.guild.domain.model.entity.Rol;
import com.guild.guild.resources.createdResource.CreatedRolResource;
import com.guild.guild.resources.resources.RolResource;
import com.guild.shared.mapping.EnhancedModelMapper;

public class RolMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public RolResource toResource(Rol model){
        return mapper.map(model, RolResource.class);
    }

    public Rol toModel(CreatedRolResource resource){
        return mapper.map(resource, Rol.class);
    }

    public List<RolResource> modelListToResource(List<Rol> modelList){
        return mapper.mapList(modelList, RolResource.class);
    }
    
}
