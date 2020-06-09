package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserMessageRepository extends CrudRepository<UserMessage, Integer> {
    public List<UserMessage> findAllBySenderId(Integer id);
    public List<UserMessage> findAllByRecipientId(Integer id);
    public Integer countAllByRecipientIdAndSeenStatusIsFalse(Integer id);

    @Query(value = "SELECT id FROM user_message ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public Integer findMaximumId();

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMessage m WHERE m.recipient=?1 OR m.sender=?1")
    public void deleteAllByUser(User user);

    public boolean existsByRecipientId(Integer id);
    public boolean existsBySenderId(Integer id);
}
