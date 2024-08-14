package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.entity.Account;
import com.example.entity.Message;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController 
{
    //1
    @PostMapping("/register")
    private @ResponseBody ResponseEntity<Account> postRegisterHandler(@RequestBody Account acc)
    {   
        if(acc.getAccountId() != null)
        {
            return new ResponseEntity<>(acc, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    //2
    @PostMapping("/login")
    private Account postLogiAccountHandler(@RequestBody Account acc)
    {
        return null;
    }

    //3
    @PostMapping("/messages")
    private Message postMessageHandler(@RequestBody Message msg)
    {
        return null;
    }

    //4
    @GetMapping("/messages")
    private List<Message> getAllMessagesHandler()
    {
        return null;
    }

    //5
    @GetMapping("/messages/{messageId}")
    private Message getMessageById(@PathVariable int id)
    {
        return null;
    }

    //6
    @DeleteMapping("/messages/{messageId}")
    private int deleteMessageHandler(@PathVariable int id)
    {
        return 0;
    }

    //7
    @PatchMapping("/messages/{messageId}")
    private int updateMessageHandler(@PathVariable int id)
    {
        return 0;
    }

    //8
    @GetMapping("/accounts/{accountId}/messages")
    private List<Message> getAllMessagesByAccountHandler(@PathVariable int id)
    {
        return null;
    }



}
