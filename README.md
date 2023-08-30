# help-request-consumer

The ask-help-message-consumer project is a Spring Boot application that start consuming XML messages from ActiveMQ, converting them to JSON format, and performing the necessary operations based on the message type.


Usage:

  Before running the application:
   -Need to run activemq docker: Apache ActiveMQ :docker run -p 61616:61616 -p 8161:8161 rmohr/activemq apache ActiveMQ browse http://localhost:8161/admin/queues.jsp.
    -run PostgreSQL docker 
      pull docker: https://hub.docker.com/_/postgres docker pull postgres:
      run docker image :docker run --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres
      download db tool like dbeaver:https://dbeaver.io/download/
      create new connection.

    -Build and run the project

