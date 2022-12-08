package com.guild.guild.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guild.games.domain.model.entity.Game;
import com.guild.games.domain.service.GameService;
import com.guild.guild.domain.model.entity.Guild;
import com.guild.guild.domain.persistence.GuildRepository;
import com.guild.guild.domain.services.GuildService;
import com.guild.shared.exception.ResourceNotFoundException;
import com.guild.shared.exception.ResourceValidationException;

@Service
public class GuildServiceImpl implements GuildService {

    private static final String ENTITY = "guild";
    private final GameService gameService;
    private final GuildRepository repository;
    private final Validator validator;

    public GuildServiceImpl(GuildRepository repository, Validator validator, GameService gameService) {
        this.repository = repository;
        this.validator = validator;
        this.gameService = gameService;
    }


    @Override
    public List<Guild> getAll() {
        return repository.findAll();
    }

    @Override
    public Guild getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public List<Guild> getByGameId(Long gameId) {

        if (gameService.getById(gameId)==null)
            throw new ResourceNotFoundException("Game", gameId);

        return repository.findByGameId(gameId);
    }

    @Override
    public List<Guild> getByName(String name) {

        return repository.findByNameContaining(name);
    }


    @Override
    public List<Guild> getByGameName(String gameName) {
        
        List<Game> games = gameService.getByName(gameName);
        List<Guild> guilds = new ArrayList<Guild>();

        if(games.isEmpty())
            throw new ResourceNotFoundException("Don't exist Games with this name");
        
        for (Game game : games) {
            guilds.addAll(getByGameId(game.getId()));
        }

        return guilds;

    }

    @Override
    public Guild create(Guild request) {
        
        Set<ConstraintViolation<Guild>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return repository.save(request);
    }

    @Override
    public Guild update(Long id, Guild request) {
        
        Set<ConstraintViolation<Guild>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!repository.existsById(id))
            throw new ResourceNotFoundException(ENTITY, id);

        return repository.findById(id).map(
                guild -> repository.save(guild
                        .withName(request.getName())
                        .withDescription(request.getDescription())
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
