package com.example.camel.helpconsumer.routes;

import com.example.camel.helpconsumer.entities.HelpRequestEntity;
import com.example.camel.helpconsumer.routes.processors.SimpleEmailProcessor;
import com.example.camel.helpconsumer.routes.processors.SimpleTypeProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .routeId("Help-message-Route")
                .log("${body}")
                .unmarshal()
                .jacksonXml()
                .marshal().json()
                .log("${body}")
                .process(new SimpleTypeProcessor())
                .choice()
                .when(simple("${exchangeProperty.helpRequestType} == 'EMAIL'"))
                .process(new SimpleEmailProcessor())
                .to("smtps://" + mailHost + ":" + mailPort +
                        "?username=" + mailUsername +
                        "&password=" + mailPassword +
                        "&from=" + mailUsername)
                .when(simple("${exchangeProperty.helpRequestType} == 'DB'"))
                .unmarshal().json(JsonLibrary.Jackson, HelpRequestEntity.class)
                .to("jpa:com.example.camel.helpconsumer.entities.HelpRequestEntity?persistenceUnit=postgresql&flushOnSend=true")
                .otherwise()
                .log("${body}");
    }


}

