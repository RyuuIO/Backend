package com.guild.events.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guild.events.domain.service.EventService;
import com.guild.events.mapping.EventMapper;
import com.guild.events.resources.createResources.CreateEventResource;
import com.guild.events.resources.resources.EventResource;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*", methods ={
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE
})
@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    
    @Autowired
    private EventService service;

    @Autowired
    private EventMapper mapper;

    @GetMapping
    public List<EventResource> getAll(){
        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{id}")
    public EventResource getById(@PathVariable("id") Long id) {
        return mapper.toResource(service.getById(id));
    }

    @PostMapping
    public EventResource create(@RequestBody CreateEventResource resource){
        return mapper.toResource(service.create(mapper.toModel(resource)));
    }

    @PutMapping("{id}")
    public EventResource update(@PathVariable("id") Long id, @RequestBody CreateEventResource resource){
        return mapper.toResource(service.update(id, mapper.toModel(resource)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
