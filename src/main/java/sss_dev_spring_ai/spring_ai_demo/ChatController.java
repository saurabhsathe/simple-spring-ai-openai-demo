package sss_dev_spring_ai.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@RestController
public class ChatController {


    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .build();
    }

    @GetMapping("/getjokes")
    String generate(@RequestParam(value = "message", defaultValue = "Tell me a dad joke") String message) {
        try {
            return chatClient.prompt()
                    .user(message)
                    .call()
                    .content();
        } catch (RestClientException e) {
            System.err.println("Error while extracting response: " + e.getMessage());
            return "Error: Could not generate a joke.";
        }
    }

}
