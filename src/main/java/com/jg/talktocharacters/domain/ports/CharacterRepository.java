package com.jg.talktocharacters.domain.ports;

import com.jg.talktocharacters.domain.model.Character;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository {
    List<Character> findAll();
    Optional<Character> findById(Long id);
}
