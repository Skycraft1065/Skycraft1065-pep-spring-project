package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import javafx.application.Application;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController 
{

    //ApplicationContext ctx = SpringApplication.run(SocialMediaController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    //1
    @PostMapping("/register")
    private @ResponseBody ResponseEntity<Account> postRegister(@RequestBody Account account)
    {   
        Account newAccount = accountService.registerAccount(account);
        if(newAccount.getAccountId() != null)
        {
            return new ResponseEntity<>(newAccount, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    //2
    @PostMapping("/login")
    private Account postLoginAccount(@RequestBody Account acc)
    {
        return null;
    }

    //3
    @PostMapping("/messages")
    private Message postMessage(@RequestBody Message msg)
    {
        return null;
    }

    //4
    @GetMapping("/messages")
    private void getAllMessages()
    {
        List<Message> messages = messageService.getAllMessages();
    }

    //5
    @GetMapping("/messages/{messageId}")
    private ResponseEntity<Message> getMessageById(@PathVariable int messageId)
    {
        Message message = messageService.getMessageById(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    //6
    @DeleteMapping("/messages/{messageId}")
    private int deleteMessage(@PathVariable int id)
    {
        return 0;
    }

    //7
    @PatchMapping("/messages/{messageId}")
    private ResponseEntity<Integer> updateMessage(@PathVariable int messageId, @RequestBody String messageText)
    {
        //messageService.
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    //8
    @GetMapping("/accounts/{accountId}/messages")
    private List<Message> getAllMessagesByAccount(@PathVariable int id)
    {
        return null;
    }



}
