package com.guild.guild.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guild.guild.domain.services.MemberService;
import com.guild.guild.mapping.MemberMapper;
import com.guild.guild.resources.createdResource.CreatedMemberResource;
import com.guild.guild.resources.resources.MemberResource;

@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE
})
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberService service;

    @Autowired
    private MemberMapper mapper;

    @GetMapping
    public List<MemberResource> getAll() {

        return mapper.modelListToResource(service.getAll());
    }

    @GetMapping("{Id}")
    public MemberResource getById(@PathVariable("Id") Long Id) {

        return mapper.toResource(service.getById(Id));
    }

    @PostMapping
    public MemberResource create(@RequestBody CreatedMemberResource request) {
        return mapper.toResource(service.create(mapper.toModel(request)));
    }

    @PutMapping("{Id}")
    public MemberResource update(@PathVariable("Id") Long Id, @RequestBody CreatedMemberResource request) {
        return mapper.toResource(service.update(Id, mapper.toModel(request)));
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> delete(@PathVariable("Id") Long Id){

        return service.delete(Id);
    }
    
}
