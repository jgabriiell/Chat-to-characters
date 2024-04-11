package com.jg.talktocharacters.adapters.in;

import com.jg.talktocharacters.application.ListCharacterUseCase;
import com.jg.talktocharacters.domain.model.Character;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Tag(name = "Characters", description = "Talk to the most iconic characters")
@RestController
@RequestMapping("/characters")
public class ListCharactersRestController {
    private final ListCharacterUseCase listCharacterUseCase;

    public ListCharactersRestController(ListCharacterUseCase listCharacterUseCase) {
        this.listCharacterUseCase = listCharacterUseCase;
    }

    @GetMapping
    public List<Character> findAllCharacters() {
        return listCharacterUseCase.findAll();
    }
}


