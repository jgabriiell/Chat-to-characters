package com.jg.talktocharacters.application;

import com.jg.talktocharacters.domain.model.Character;
import com.jg.talktocharacters.domain.ports.CharacterRepository;

import java.util.List;

public record ListCharacterUseCase(CharacterRepository repository) {

    public List<Character> findAll() {
        return repository.findAll();
    }
}
