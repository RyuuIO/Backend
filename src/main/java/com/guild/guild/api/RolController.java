package com.guild.guild.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guild.guild.domain.services.RolService;
import com.guild.guild.mapping.RolMapper;
import com.guild.guild.resources.createdResource.CreatedRolResource;
import com.guild.guild.resources.resources.RolResource;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE
})
@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    @Autowired
    private RolService service;

    @Autowired
    private RolMapper mapper;

    /**
     * It takes a list of Rol objects, converts them to RolResource objects, and returns them
     */ 
    @GetMapping
    public List<RolResource> getAll(){
        return mapper.modelListToResource(service.getAll());
    }

    /**
     * It takes a Long id as a parameter, calls the service to get the Rol object, and then maps it to
     * a RolResource object
     */
    @GetMapping("{Id}")
    public RolResource getById(@PathVariable("Id") Long id){
        return mapper.toResource(service.getById(id));
    }

    /**
     * It takes a CreatedRolResource object, converts it to a Rol object, passes it to the service
     * layer, then maps the result to a resource and returns it
     */ 
    @PostMapping
    public RolResource create(@RequestBody CreatedRolResource request){
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    /**
     * It takes a request body, maps it to a model, passes it to the service
     * layer, then maps the updated model to a resource and returns it
     */
    @PutMapping("{Id}")
    public RolResource update(@PathVariable("Id") Long id, @RequestBody CreatedRolResource request){
        return mapper.toResource(service.update(id, mapper.toModel(request)));
    }

    //It deletes the object with the given id
    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable("Id") Long id){
        return service.delete(id);
    }
}
