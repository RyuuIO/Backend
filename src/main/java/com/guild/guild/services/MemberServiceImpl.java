package com.guild.guild.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guild.guild.domain.model.entity.Member;
import com.guild.guild.domain.persistence.MemberRepository;
import com.guild.guild.domain.services.MemberService;
import com.guild.shared.exception.ResourceNotFoundException;
import com.guild.shared.exception.ResourceValidationException;

@Service
public class MemberServiceImpl implements MemberService{

    private static final String ENTITY = "Member"; 
    private final MemberRepository repository;
    private final Validator validator;

    public MemberServiceImpl(MemberRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<Member> getAll() {
        return repository.findAll();
    }

    @Override
    public Member getById(long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Member create(Member request) {

        Set<ConstraintViolation<Member>> violations = validator.validate(request);

        if(!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return repository.save(request);
    }

    @Override
    public Member update(Long id, Member request) {
        Set<ConstraintViolation<Member>> violations = validator.validate(request);

        if(!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(ENTITY, id);
        }

        return repository.findById(id).map(
            member -> repository.save(member
                .withGuild(request.getGuild())
                .withPlayer(request.getPlayer())
                .withRol(request.getRol())
            )
        ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(ENTITY, id);
        }

        return repository.findById(id).map(
            member -> {
                repository.deleteById(id);
                return ResponseEntity.ok().build();
            }
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));

    }
    
}
