package com.example.demo.repository;

import com.example.demo.model.BlockedUser;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockedUserRepository extends CrudRepository<BlockedUser, Integer> {
    public List<BlockedUser> findAllByBlockerUserId(Integer id);
    public List<BlockedUser> findAllByDateBlockedBetween(LocalDate from, LocalDate to);
    public Integer countAllByBlockerUserId(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM BlockedUser b WHERE b.blockerUser = ?1")
    public void deleteAllByBlockerUser(User user);

    public boolean existsByBlockerUserId(Integer id);
    public boolean existsByBlockedUserId(Integer id);

    @Query(value = "SELECT id FROM blocked_user ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public Integer findMaximumId();
}
