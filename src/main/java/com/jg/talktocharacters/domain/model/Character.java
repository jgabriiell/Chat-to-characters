package com.jg.talktocharacters.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Character {

    private Long id;
    private String name;
    private String role;
    private String lore;
    private String imageUrl;

    public String generateContextByQuestion(String question) {
        return """
         Question: %s
         Character: %s
         Role: %s
         Lore: %s
         """.formatted(question, this.name, this.role, this.role);
    }
}
