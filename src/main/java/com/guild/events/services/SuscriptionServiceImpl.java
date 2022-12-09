package com.guild.events.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guild.events.domain.model.entity.Suscription;
import com.guild.events.domain.repository.SuscriptionRepository;
import com.guild.events.domain.service.SuscriptionService;
import com.guild.shared.exception.ResourceNotFoundException;
import com.guild.shared.exception.ResourceValidationException;

@Service
public class SuscriptionServiceImpl implements SuscriptionService{

    private static final String ENTITY = "Suscription";
    private final SuscriptionRepository repository;
    private final Validator validator;

    public SuscriptionServiceImpl(SuscriptionRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Suscription> getAll() {
        return repository.findAll();
    }

    @Override
    public Suscription getById(Long id) {
        return repository.findById(id).
            orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Suscription create(Suscription request) {
        
        Set<ConstraintViolation<Suscription>> violations = validator.validate(request);

        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }

        return repository.save(request);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException(ENTITY, id);
        }

        return repository.findById(id).map(
            suscription -> {
                repository.delete(suscription);
                return ResponseEntity.ok().build();
            }
        ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }
}
