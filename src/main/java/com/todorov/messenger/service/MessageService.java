package com.todorov.messenger.service;

import com.todorov.messenger.database.DatabaseClass;
import com.todorov.messenger.exception.DataNotFountException;
import com.todorov.messenger.model.Message;

import java.util.*;

/**
 * Created by Alex on 04.10.2016.
 */
public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService(){}

    public List<Message> getAllMessages(){
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year){
        List<Message> messageForYear = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(Message message : messages.values()){
            calendar.setTime(message.getCreated());
            if (calendar.get(Calendar.YEAR) == year){
                messageForYear.add(message);
            }
        }
        return messageForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size){
        List<Message> list = new ArrayList<>(messages.values());
        if(start + size > list.size()){
            return new ArrayList<>();
        }
        return  list.subList(start, start + size);
    }

    public Message getMessage(long id) throws DataNotFountException{
        Message message = messages.get(id);
        if (message==null){
            throw new DataNotFountException("Message with ID " + id + " not found");
        }
        else {
            return message;
        }
    }

    public Message addMessage(Message message){
        message.setId(messages.size() + 1);
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage (Message message){
        if(message.getId() <= 0){
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public void removeMessage(long id){
        messages.remove(id);
    }
}
