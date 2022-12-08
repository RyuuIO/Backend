package com.guild.events.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guild.events.domain.model.entity.Happening;
import com.guild.events.domain.repository.EventRepository;
import com.guild.events.domain.service.EventService;
import com.guild.shared.exception.ResourceNotFoundException;
import com.guild.shared.exception.ResourceValidationException;

@Service
public class EventServiceImpl implements EventService {

    private static final String ENTITY = "Event";
    private final EventRepository repository;
    private final Validator validator;


    public EventServiceImpl(EventRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Happening> getAll() {
        return repository.findAll();
    }

    @Override
    public Happening getById(long id) {
        return repository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Happening create(Happening request) {
        Set<ConstraintViolation<Happening>> violations = validator.validate(request);

        if (!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }

        return repository.save(request);
    }

    @Override
    public Happening update(Long id, Happening request) {
        Set<ConstraintViolation<Happening>> violations = validator.validate(request);

        if (!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }

        if (!repository.existsById(id)){
            throw new ResourceNotFoundException(ENTITY, id);
        }

        return repository.findById(id).map(
            event-> repository.save(event
                .withDate(request.getDate())
                .withDescription(request.getDescription())
                .withHour(request.getHour())
                .withTitle(request.getTitle())
            )
        ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException(ENTITY, id);
        }

        return repository.findById(id).map(
            event -> {
                repository.delete(event);
                return ResponseEntity.ok().build();
            }
        ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }


    
}
