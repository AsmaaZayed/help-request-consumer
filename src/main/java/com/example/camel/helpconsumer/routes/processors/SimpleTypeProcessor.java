package com.example.camel.helpconsumer.routes.processors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SimpleTypeProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonMessage = exchange.getIn().getBody(String.class);
        // Use a JSON library to extract type field
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonMessage);
        exchange.setProperty("helpRequestType", jsonNode.get("type").asText());
    }

}
