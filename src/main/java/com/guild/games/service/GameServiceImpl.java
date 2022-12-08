package com.guild.games.service;

import com.guild.games.domain.model.entity.Game;
import com.guild.games.domain.persistence.GameRepository;
import com.guild.games.domain.service.GameService;
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
public class GameServiceImpl implements GameService {

    private static final String ENTITY="Game";
    private final GameRepository repository;
    private final Validator validator;
    private final GenderService genderService;

    public GameServiceImpl(GameRepository repository, Validator validator, GenderService genderService) {
        this.repository = repository;
        this.validator = validator;
        this.genderService = genderService;
    }

    @Override
    public List<Game> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Game> getByGenderId(Long genderId) {

        if (genderService.getById(genderId)==null)
            throw new ResourceNotFoundException("Gender", genderId);

        return repository.findByGenderId(genderId);

    }

    @Override
    public List<Game> getByName(String name) {

        return repository.findByNameContaining(name);

    }

    @Override
    public Game getById(Long id) {

        return repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,id));
    }

    @Override
    public Game create(Game request) {
        Set<ConstraintViolation<Game>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public Game update(Long id, Game request) {

        Set<ConstraintViolation<Game>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                game -> repository.save(game
                        .withName(request.getName())
                        .withImage(request.getImage())
                )).orElseThrow(
                ()->new ResourceNotFoundException(ENTITY, id)
        );

    }

    @Override
    public ResponseEntity<?> delete(Long id) {

        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                        game -> {
                            repository.delete(game);
                            return ResponseEntity.ok().build();
                        })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
