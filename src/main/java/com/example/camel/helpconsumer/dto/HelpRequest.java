package com.example.camel.helpconsumer.dto;

public class HelpRequest {
    private Long id;
    private String message;
    private String name;
    private String email;
    private String type;
    public HelpRequest() {

    }

    public HelpRequest( String message, String name,String email,String type) {
        super();
        this.message = message;
        this.name=name;
        this.email = email;
        this.type=type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Help [ name=" + name +  ", message=" + message + ", email=" + email  + "]";
    }

}
