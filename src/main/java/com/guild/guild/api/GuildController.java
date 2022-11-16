package com.guild.guild.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guild.guild.domain.services.GuildService;
import com.guild.guild.mapping.GuildMapper;
import com.guild.guild.resources.createdResource.CreatedGuildResource;
import com.guild.guild.resources.resources.GuildResource;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE
})
@RestController
@RequestMapping("/api/v1/guilds")
public class GuildController {

    @Autowired
    private GuildService service;

    @Autowired
    private GuildMapper mapper;

    @GetMapping
    public List<GuildResource> getAll() {

        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public GuildResource getById(@PathVariable("Id") Long Id) {

        return mapper.toResource(service.getById(Id));
    }

    @GetMapping("name/{name}")
    public List<GuildResource> getByName(@PathVariable("name") String name) {

        return mapper.modelListToResource(service.getByName(name));
    }

    @GetMapping("gameId/{gameId}")
    public List<GuildResource> getByGameId(@PathVariable("gameId") Long gameId) {

        return mapper.modelListToResource(service.getByGameId(gameId));
    }

    @GetMapping("gameName/{gameName}")
    public List<GuildResource> getByGameName(@PathVariable("gameName") String gameName){
        return mapper.modelListToResource(service.getByGameName(gameName));
    }

    @PostMapping
    public GuildResource create(@RequestBody CreatedGuildResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public GuildResource update(@PathVariable("Id") Long Id, @RequestBody CreatedGuildResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable("Id") Long Id){

        return service.delete(Id);
    }
    

}
