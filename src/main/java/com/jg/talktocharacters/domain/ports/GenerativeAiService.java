package com.jg.talktocharacters.domain.ports;

public interface GenerativeAiService {

    String generateContent(String objective, String content);
}
