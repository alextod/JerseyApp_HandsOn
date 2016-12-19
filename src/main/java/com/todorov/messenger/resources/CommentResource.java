package com.todorov.messenger.resources;

import com.todorov.messenger.model.Comment;
import com.todorov.messenger.service.CommentService;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Alex on 05.10.2016.
 */
@Path("/")
public class CommentResource {

    CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId){
        return commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getCommentById(@PathParam("commentId") long commentId,
                                 @PathParam("messageId") long messageId){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("Message ID - ");
//        stringBuilder.append(messageId);
//        stringBuilder.append('\n');
//        stringBuilder.append("Comment ID - ");
//        stringBuilder.append(commentId);
//        return stringBuilder.toString();

        return commentService.getComment(messageId, commentId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId, Comment comment){
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long commentId,
                                 Comment comment){
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }


    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
        commentService.removeComment(messageId, commentId);
    }
}
