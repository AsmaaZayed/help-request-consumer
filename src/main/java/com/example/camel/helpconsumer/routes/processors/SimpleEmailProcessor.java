package com.example.camel.helpconsumer.routes.processors;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SimpleEmailProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonMessage = exchange.getIn().getBody(String.class);

        // Use a JSON library to extract name, email and message fields
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonMessage);

        String name = jsonNode.get("name").asText();
        String email = jsonNode.get("email").asText();
        String message = jsonNode.get("message").asText();


        // Create and send the email
        String subject = "Need Help from " + name;
        String body = message+" "+name;   // Set the email headers
        exchange.getIn().setHeader("Subject", subject);
        exchange.getIn().setHeader("To", email);

        exchange.getIn().setBody(body);
    }

}
