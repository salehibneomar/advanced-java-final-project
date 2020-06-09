package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserFavorite;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserFavouriteRepository extends CrudRepository<UserFavorite, Integer> {
    public List<UserFavorite> findAllByFavouriteMakerId(Integer id);
    public List<UserFavorite> findAllByDateAddedBetween(LocalDate from, LocalDate to);
    public Integer countAllByFavouriteMakerId(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserFavorite u WHERE u.favouriteMaker = ?1")
    public void deleteAllByFavouriteMaker(User user);

    public boolean existsByFavouriteMakerId(Integer id);
    public boolean existsByFavouriteUserId(Integer id);

    @Query(value = "SELECT id FROM user_favorite ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public Integer findMaximumId();

}
