package com.guild.games.service;

import com.guild.games.domain.model.entity.Gender;
import com.guild.games.domain.persistence.GenderRepository;
import com.guild.games.domain.service.GenderService;
import com.guild.shared.exception.ResourceNotFoundException;
import com.guild.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class GenderServiceImpl implements GenderService {

    private static final String ENTITY="Gender";
    private final GenderRepository repository;
    private final Validator validator;

    public GenderServiceImpl(GenderRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Gender> getAll() {
        return repository.findAll();
    }

    @Override
    public Gender getById(Long id) {

        return repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,id));
    }

    @Override
    public Gender create(Gender request) {
        Set<ConstraintViolation<Gender>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public Gender update(Long id, Gender request) {

        Set<ConstraintViolation<Gender>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                gender -> repository.save(request
                        .withName(request.getName())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );

    }

    @Override
    public ResponseEntity<?> delete(Long id) {

        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                        gender -> {
                            repository.delete(gender);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
