package com.guild.guild.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guild.guild.domain.model.entity.Member;
import com.guild.guild.resources.createdResource.CreatedMemberResource;
import com.guild.guild.resources.resources.MemberResource;
import com.guild.shared.mapping.EnhancedModelMapper;

public class MemberMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public MemberResource toResource(Member model){
        return mapper.map(model, MemberResource.class);
    }
    
    public Member toModel(CreatedMemberResource resource){
        return mapper.map(resource, Member.class);
    }

    public List<MemberResource> modelListToResource(List<Member> modelList){
        return mapper.mapList(modelList, MemberResource.class);
    }

}
