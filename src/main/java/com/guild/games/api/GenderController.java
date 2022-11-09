package com.guild.games.api;

import com.guild.games.domain.service.GenderService;
import com.guild.games.mapping.GenderMapper;
import com.guild.games.resource.createResource.CreateGenderResource;
import com.guild.games.resource.resource.GenderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
})
@RestController
@RequestMapping("/api/v1/gender")
public class GenderController {

    @Autowired
    private GenderService service;

    @Autowired
    private GenderMapper mapper;

    @GetMapping
    public List<GenderResource> getAll() {

        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public GenderResource getById(@PathVariable("Id") Long Id) {

        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public GenderResource create(@RequestBody CreateGenderResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public GenderResource update(@PathVariable("Id") Long Id, @RequestBody CreateGenderResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable("Id") Long Id){

        return service.delete(Id);
    }

}
