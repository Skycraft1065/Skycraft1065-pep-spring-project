package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

@RestController
public class SocialMediaController 
{
    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    /**
     * Handles registering a new Account to the database
     * @param account object w no ID to be inserted into the database
     * @return a ResponseEntity page which utilizes an Account object
     */
    @PostMapping("/register")
    private ResponseEntity<Account> postRegister(@RequestBody Account account)
    {   
        Account newAccount = accountService.registerAccount(account);

        //If the account isn't null, and the password does equal (DUPLICATE)
        //I'm 95% certain parenthesis are a forbidden character in passwords;
        if(newAccount != null && newAccount.getPassword().equals("(DUPLICATE)"))
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        else if(newAccount != null) //If the account isn't null and the password doesn't equal (DUPLICATE)
        {
            return ResponseEntity.status(HttpStatus.OK).body(newAccount);
        }
        else //If the account is null
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Handles a login attempt to the server
     * @param loginAttempt account object w no ID. Models a login attempt by a user
     * @return a ResponseEntity page which utilizes an Account object
     */
    @PostMapping("/login")
    private ResponseEntity<Account> postLoginAccount(@RequestBody Account loginAttempt)
    {
        Account account = accountService.loginAccount(loginAttempt);
        
        //If the account isn't null, and the username and password match on both accounts, proceed. Otherwise, unauthorized
        if(account != null && account.getUsername().equals(loginAttempt.getUsername()) && account.getPassword().equals(loginAttempt.getPassword()))
        {
            return ResponseEntity.status(HttpStatus.OK).body(account);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /**
     * Handles posting new messages to the server
     * @param message w/o a database ID
     * @return a ResponseEntity utilizing a Message object
     */
    @PostMapping("/messages")
    private ResponseEntity<Message> postMessage(@RequestBody Message message)
    {
        //Gets and checks if the account in question exists. Otherwise, bad request
        Account checkExists = accountService.getAccountById(message.getPostedBy());
        if(checkExists == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Message addedMessage = messageService.postMessage(message);
        
        //If the message isn't null, proceed. Otherwise, bad request
        if(addedMessage!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(addedMessage);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    /**
     * Handles getting all messages from the database
     * @return a ResponseEntity utilizing a List of Message objects
     */
    @GetMapping("/messages")
    private ResponseEntity<List<Message>> getAllMessages()
    {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    /**
     * Handles getting a message by its ID. 
     * @param messageId integer which is used as the ID in the database
     * @return a ResponseEntity utilizing a Message object.
     */
    @GetMapping("/messages/{messageId}")
    private ResponseEntity<Message> getMessageById(@PathVariable int messageId)
    {
        Message message = messageService.getMessageById(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    /**
     * Handles a delete message request
     * @param messageId integer which represents the ID of the message to be deleted
     * @return a ResponseEntity utilizing an Integer which represents the amount of rows edited
     */
    @DeleteMapping("/messages/{messageId}")
    private ResponseEntity<Integer> deleteMessage(@PathVariable int messageId)
    {
        int rowsUpdated = messageService.deleteMessage(messageId);

        if (rowsUpdated == 1)
        {
            return ResponseEntity.status(HttpStatus.OK).body(rowsUpdated);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    /**
     * Handles an update message request
     * @param messageId integer which represents the ID of a message to be updated
     * @param newMessage string which is the new text that replaces the old
     * @return a ResponseEntity utilizing an Integer which represents the amount of rows edited
     */
    @PatchMapping("/messages/{messageId}")
    private ResponseEntity<Integer> updateMessage(@PathVariable int messageId, @RequestBody Message newMessage)
    {
        int rowsUpdated = messageService.updateMessage(messageId, newMessage);

        if (rowsUpdated == 1)
        {
            return ResponseEntity.status(HttpStatus.OK).body(rowsUpdated);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rowsUpdated);
        }
    }

    /**
     * Handles a request to get all messages by an account
     * @param accountId
     * @return
     */
    @GetMapping("/accounts/{accountId}/messages")
    private ResponseEntity<List<Message>> getAllMessagesByAccount(@PathVariable int accountId)
    {
        List<Message> messages = messageService.getAllMessagesByAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }
}
