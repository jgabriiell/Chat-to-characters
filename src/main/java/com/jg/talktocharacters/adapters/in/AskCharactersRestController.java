package com.jg.talktocharacters.adapters.in;

import com.jg.talktocharacters.application.AskCharacterUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Characters", description = "Talk to the most iconic characters")
@RestController
@RequestMapping("/characters")
public class AskCharactersRestController {
    private final AskCharacterUseCase askCharacterUseCase;

    public AskCharactersRestController(AskCharacterUseCase askCharacterUseCase) {
        this.askCharacterUseCase = askCharacterUseCase;
    }

    @PostMapping("/{id}/ask")
    public AskCharacterResponse askChampion(@PathVariable Long id, @RequestBody AskChampionRequest request) {
        String answer = askCharacterUseCase.askCharacter(id, request.question());
        return new AskCharacterResponse(answer);
    }

    public record AskChampionRequest(String question) {}
    public record AskCharacterResponse(String answer) {}
}


