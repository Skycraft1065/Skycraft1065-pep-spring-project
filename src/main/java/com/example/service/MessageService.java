package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService 
{
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message)
    {
        return null;
    }

    public List<Message> getAllMessages()
    {
       return messageRepository.findAll();
    }

    public Message getMessageById(int id)
    {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent())
        {
            return optionalMessage.get();
        }
        else
        {
            return null;
        }
    }

    public void deleteMessage()
    {

    }

    public void updateMessage(int id, String text) 
    {
        
    }

    public List<Message> getAllMessagesByAccount()
    {
        return null;
    }
}
