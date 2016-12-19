package com.todorov.messenger.service;

import com.todorov.messenger.database.DatabaseClass;
import com.todorov.messenger.model.Comment;
import com.todorov.messenger.model.ErrorMessage;
import com.todorov.messenger.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by atodorov on 10/5/2016.
 */
public class CommentService {
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId){
        return new ArrayList<>(messages.get(messageId).getComments().values());
    }

    public Comment getComment(long messageId, long commentId){
        ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "http://java.com");
        Response response = Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

        Message message = messages.get(messageId);
        if (message == null){
            throw new WebApplicationException(response);
        }
        Map<Long, Comment> comments = message.getComments();
        Comment comment = comments.get(commentId);
        if(comment == null){
            throw new NotFoundException(response);
        }
        return comment;
    }

    public Comment addComment(long messageId, Comment comment){
        Map<Long, Comment> commentMap = messages.get(messageId).getComments();
        comment.setId(commentMap.size() + 1L);
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment){
        Map<Long, Comment> commentMap = messages.get(messageId).getComments();
        if (comment.getId() <=0){
            return null;
        }
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId){
        Map<Long, Comment> commentMap = messages.get(messageId).getComments();
        return commentMap.remove(commentId);
    }
}
