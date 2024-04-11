package com.jg.talktocharacters.domain.exceptions;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(Long characterId) {
        super("Champion %d not found".formatted(characterId));
    }
}
