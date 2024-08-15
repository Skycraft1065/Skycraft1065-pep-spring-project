package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

import com.example.entity.Account;

@Service
public class AccountService 
{
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accRepo)
    {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account)
    {
        //If the given account's password is longer than 4 and the username isn't blank, proceed
        if (account.getPassword().length() >= 4 && account.getUsername() != "" && accountRepository.findAccountByUsername(account.getUsername()) != null)
        {
            return accountRepository.save(account);
        }
        else
        {
            return null;
        }
    }

    public Account loginAccount(Account account)
    {
        return null;
    }

    public Account findAccountByUsername(String username)
    {
        Account acc = accountRepository.findAccountByUsername(username);
        return acc;
    }

}
