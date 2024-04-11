package com.jg.talktocharacters.application;

import com.jg.talktocharacters.adapters.out.GenerativeAiImplemented;
import com.jg.talktocharacters.domain.exceptions.CharacterNotFoundException;
import com.jg.talktocharacters.domain.model.Character;
import com.jg.talktocharacters.domain.ports.CharacterRepository;

public record AskCharacterUseCase(CharacterRepository repository, GenerativeAiImplemented genAiImpl) {

    public String askCharacter(Long characterId, String question) {

        Character character = repository.findById(characterId).orElseThrow(() -> new CharacterNotFoundException(characterId));
        String context = character.generateContextByQuestion(question);

        String objective = """
                Act as an assistant with the ability to behave like some characters as Geralt of Rivia, Cirilla of Cintra, Yennerfer of
                Vengerberg, Batman, Jocker and Michael Scott.
                Answer questions incorporating each character personality and the way they would do.
                Below is the question, the name of the character and their respective lore (history):
                """;
        return genAiImpl.generateContent(objective, context);
    }
}
