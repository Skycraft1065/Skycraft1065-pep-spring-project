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

    /**
     * Posts a message to the database
     * @param message 
     * @return message obj posted to the database
     */
    public Message postMessage(Message message)
    {
        //If the message is less than 255 characters and isn't blank;
        if(message.getMessageText().length() <= 255 && message.getMessageText() != "")
        {
            return messageRepository.save(message);
        }
        else
        {
            return null;
        }
    }

    /**
     * Gets all messages from the database
     * @return a List of messages
     */
    public List<Message> getAllMessages()
    {
       return messageRepository.findAll();
    }

    /**
     * Gets a message by its ID
     * @param id
     * @return message obj
     */
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

    /**
     * Deletes a message from the database
     * @param messageId
     * @return integer indicating number of changed rows
     */
    public int deleteMessage(int messageId)
    {
        //If the message exists, proceed
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent())
        {
            messageRepository.deleteById(messageId);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Updates a message's text in the database
     * @param messageId
     * @param newMessage
     * @return Integer indicating number of changed rows
     */
    public int updateMessage(int messageId, Message newMessage) 
    {
        //If the message exists, proceed
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent() && newMessage.getMessageText().length() <= 255 && newMessage.getMessageText() != "")
        {
            Message commitNewMessage = optionalMessage.get();
            commitNewMessage.setMessageText(newMessage.getMessageText());
            messageRepository.save(commitNewMessage);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Gets all the messages by a given account
     * @param postedBy
     * @return a List of messages
     */
    public List<Message> getAllMessagesByAccount(int postedBy)
    {
        return messageRepository.getAllMessagesByAccount(postedBy);
    }
}
