package com.guild.events.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guild.events.domain.service.SuscriptionService;
import com.guild.events.mapping.SuscriptionMapper;
import com.guild.events.resources.createResources.CreateSuscriptionResource;
import com.guild.events.resources.resources.SuscriptionResource;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.DELETE
})
@RestController
@RequestMapping("api/v1/suscriptions")
public class SuscriptionController {

    @Autowired
    private SuscriptionService service;

    @Autowired
    private SuscriptionMapper mapper;

    @GetMapping
    public List<SuscriptionResource> getAll(){
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{id}")
    public SuscriptionResource getById(@PathVariable("id") Long id){
        return mapper.toResource(service.getById(id));
    }

    @PostMapping
    public SuscriptionResource create(@RequestBody CreateSuscriptionResource resource){
        return  mapper.toResource(service.create(mapper.toModel(resource)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
