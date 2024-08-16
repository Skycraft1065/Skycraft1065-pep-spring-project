package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

import com.example.entity.Account;

@Service
public class AccountService 
{
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    /**
     * Registers an account to the database
     * The account obj acts as a vehicle for a duplication indicator in the controller
     * @param account
     * @return the account post-creation OR if it's a duplicate, or null if failed for any other reason
     */
    public Account registerAccount(Account account)
    {
        //If the given account's password is longer than 4 and the username isn't blank, proceed and if the account doesn't already exist, proceed;
        if (account.getPassword().length() >= 4 && account.getUsername() != "" && accountRepository.findAccountByUsername(account.getUsername()) == null)
        {
            return accountRepository.save(account);
        }
        else if (accountRepository.findAccountByUsername(account.getUsername()) != null)
        {
            account.setPassword("(DUPLICATE)");
            return account;
        }
        else
        {
            return null;
        }
    }

    /**
     * Calls get Login in order to get login info
     * @param account
     * @return account obj
     */
    public Account loginAccount(Account account)
    {
        return accountRepository.getLogin(account.getUsername(), account.getPassword());
    }

    /**
     * Gets an account by its ID. Only currently utilized by one method in the Controller but can easily be implemented
     * @param accountId
     * @return account obj
     */
    public Account getAccountById(int accountId)
    {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent())
        {
            return optionalAccount.get();
        }
        else
        {
            return null;
        }
    }

    /**
     * Finds an account by a given username
     * @param username
     * @return account obj
     */
    public Account findAccountByUsername(String username)
    {
        Account account = accountRepository.findAccountByUsername(username);
        return account;
    }

}
