package com.jg.talktocharacters.adapters.out;

import com.jg.talktocharacters.domain.ports.GenerativeAiService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(name = "GenerativeAiImplemented", url = "${openai.base-url}", configuration = GenerativeAiImplemented.Config.class)
public interface GenerativeAiImplemented extends GenerativeAiService {

    @PostMapping("v1/chat/completions")
    OpenAiChatCompletionResp chatCompletion(OpenAiChatCompletionReq req);

    @Override
    default String generateContent(String objective, String context) {
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
                new Message("system", objective),
                new Message("user", context)
        );
        OpenAiChatCompletionReq req = new OpenAiChatCompletionReq(model, messages);

        OpenAiChatCompletionResp resp = chatCompletion(req);
        return resp.choices().getFirst().message().content();
    }

    // this record will realize requests to the external API
     record OpenAiChatCompletionReq(String model, List<Message> messages) {};
     record Message(String role, String content) {};

     // this record will give back a response from the API
     record OpenAiChatCompletionResp(List<Choice> choices){}
     record Choice(Message message){}

    class Config {
         @Bean
         public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey));
         }
    }
}
