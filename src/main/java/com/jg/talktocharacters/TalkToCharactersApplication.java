package com.jg.talktocharacters;

import com.jg.talktocharacters.adapters.out.GenerativeAiImplemented;
import com.jg.talktocharacters.application.AskCharacterUseCase;
import com.jg.talktocharacters.application.ListCharacterUseCase;
import com.jg.talktocharacters.domain.ports.CharacterRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class TalkToCharactersApplication {
	public static void main(String[] args) {
		SpringApplication.run(TalkToCharactersApplication.class, args);
	}

	@Bean
	public ListCharacterUseCase provideListCharacterUseCase(CharacterRepository repository) {
		return new ListCharacterUseCase(repository);
	}

	@Bean
	public AskCharacterUseCase provideAskCharacterUseCase(CharacterRepository repository, GenerativeAiImplemented genAiImpl) {
		return new AskCharacterUseCase(repository, genAiImpl);
	}

}
