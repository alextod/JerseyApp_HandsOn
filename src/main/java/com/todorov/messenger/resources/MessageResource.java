package com.todorov.messenger.resources;

import com.todorov.messenger.model.Message;
import com.todorov.messenger.resources.beans.MessageFilterBean;
import com.todorov.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by Alex on 04.10.2016.
 */
@Path("/messages")
public class MessageResource {
    private MessageService messageService = new MessageService();

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
        if (filterBean.getYear() > 0){
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getStart() >= 0 && filterBean.getSize() > 0){
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return messageService.getAllMessages();
    }


    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id){
        messageService.removeMessage(id);
    }

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response addMessage(Message message, @Context UriInfo uriInfo){
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI newUri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(newUri)
                .entity(newMessage)
                .build();
        //return Response.created(new URI("/messenger/webapi/messages/" + newMessage.getId())).entity(newMessage).build();
        //return Response.status(Response.Status.CREATED).entity(messageService.addMessage(message)).build();
        //return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Message updateMessage(@PathParam("messageId") long id, Message message){
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @GET
    @Path("/{messageId}")
    @Produces (MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") long id){
        return messageService.getMessage(id);
    }

    @Path("/{messageId}/comments")
    @Produces (MediaType.TEXT_PLAIN)
    public CommentResource getCommentResource(){
        return new CommentResource();
    }
}
