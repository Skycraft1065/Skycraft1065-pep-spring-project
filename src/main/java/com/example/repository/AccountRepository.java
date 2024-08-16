package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>
{
    /**
     * Queries an account by a given username
     * @param username
     * @return Account object
     */
    @Query("FROM Account WHERE username = :usernameVar")
    Account findAccountByUsername(@Param("usernameVar") String username);

    /**
     * Queries an account by a given username and password
     * @param username
     * @param password
     * @return Account object
     */
    @Query("FROM Account WHERE username = :usernameVar AND password = :passwordVar")
    Account getLogin(@Param("usernameVar") String username, @Param("passwordVar") String password);
}
