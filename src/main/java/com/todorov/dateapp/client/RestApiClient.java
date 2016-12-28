package com.todorov.dateapp.client;

import com.todorov.messenger.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Alex on 18.12.2016.
 */
public class RestApiClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        WebTarget baseTarget = client.target("http://localhost:8080/webapi/");
        WebTarget messagesTarget = baseTarget.path("messages");
        WebTarget singeMessageTarget = messagesTarget.path("{messageId}");

        WebTarget target = singeMessageTarget.resolveTemplate("messageId", "1");
        Builder builder = target.request(MediaType.APPLICATION_JSON);
        Message response = builder.get(Message.class);

        Message message = singeMessageTarget
                .resolveTemplate("messageId", "2")
                .request(MediaType.APPLICATION_JSON).
                        get(Message.class);

        System.out.println(response.getMessage());
        System.out.println(message.getMessage());


        Message postMessage = new Message(3, "My new message from JAX-RS client", "Alexey");
        Response response1 = messagesTarget.request().post(Entity.json(postMessage));
        System.out.println(response1.readEntity(Message.class));
    }
}
