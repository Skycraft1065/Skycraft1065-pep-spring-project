package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService 
{
    MessageRepository msgRepo;

    @Autowired
    public MessageService(MessageRepository msgRepo)
    {
        this.msgRepo = msgRepo;
    }

    public List<Message> getAllMessages()
    {
       //return msgRepo.;
    }
}
