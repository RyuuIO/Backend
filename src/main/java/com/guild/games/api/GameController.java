package com.guild.games.api;

import com.guild.games.domain.service.GameService;
import com.guild.games.mapping.GameMapper;
import com.guild.games.resource.createResource.CreateGameResource;
import com.guild.games.resource.resource.GameResource;
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
@RequestMapping("/api/v1/games")
public class GameController {
    @Autowired
    private GameService service;

    @Autowired
    private GameMapper mapper;

    @GetMapping
    public List<GameResource> getAll() {

        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("name/{name}")
    public List<GameResource> getByName(@PathVariable("name") String name) {

        return mapper.modelListToResource(service.getByName(name));
    }

    @GetMapping("genderId/{genderId}")
    public List<GameResource> getByGenderId(@PathVariable("genderId") Long genderId) {

        return mapper.modelListToResource(service.getByGenderId(genderId));
    }

    @GetMapping("{Id}")
    public GameResource getById(@PathVariable("Id") Long Id) {

        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public GameResource create(@RequestBody CreateGameResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public GameResource update(@PathVariable("Id") Long Id, @RequestBody CreateGameResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable("Id") Long Id){

        return service.delete(Id);
    }
}
