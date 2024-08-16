package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>
{
    /**
     * Queries messages by the ID of who posted it
     * @param postedBy
     * @return a List of Messages
     */
    @Query("FROM Message WHERE postedBy = :postedByVar")
    List<Message> getAllMessagesByAccount(@Param("postedByVar") int postedBy);
}
