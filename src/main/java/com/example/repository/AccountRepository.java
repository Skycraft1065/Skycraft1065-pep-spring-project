package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>
{
    @Query(nativeQuery = true, value = "SELECT * FROM Account WHERE username = ?")
    Account findAccountByUsername(String username);
}
