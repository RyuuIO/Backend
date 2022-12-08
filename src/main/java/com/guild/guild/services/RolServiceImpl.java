package com.guild.guild.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guild.guild.domain.model.entity.Rol;
import com.guild.guild.domain.persistence.RolRepository;
import com.guild.guild.domain.services.RolService;
import com.guild.shared.exception.ResourceNotFoundException;
import com.guild.shared.exception.ResourceValidationException;

@Service
public class RolServiceImpl implements RolService {

    private static final String ENTITY = "Rol";

    private final RolRepository repository;
    private final Validator validator;
    
    public RolServiceImpl(RolRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;

    }

    //It returns a list of all the roles in the database
    @Override
    public List<Rol> getAll() {
        return repository.findAll();
    }

    //If the id exists in the database, return the object, otherwise throw an exception
    @Override
    public Rol getById(long id) {
        return repository.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException(ENTITY,id));

    }
    
    //It validates the request object and if it's valid, it saves it to the database
    @Override
    public Rol create(Rol request) {
        Set<ConstraintViolation<Rol>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);
        
        return repository.save(request);
    }

    //If the request is valid, and the id exists, then update the entity with the request
    @Override
    public Rol update(Long id, Rol request) {
        Set<ConstraintViolation<Rol>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        if(!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY,id);

        return repository.findById(id).map(
            rol->repository.save(rol
                .withName(request.getName())
            )
        ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    //If the id exists, delete it, otherwise throw an exception
    @Override
    public ResponseEntity<?> delete(Long id) {
        
        if(!repository.existsById(id))
            throw   new ResourceNotFoundException(ENTITY,id);
        
        return repository.findById(id).map(
                rol -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }
            ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }
    
}
