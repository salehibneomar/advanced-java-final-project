package com.example.demo.repository;

import com.example.demo.model.Gender;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE id NOT IN(SELECT blocked_user_id FROM blocked_user WHERE blocker_user_id=?1) AND id!=?1 ", nativeQuery = true)
    public List<User> findAllByFilteringBlockIdAndOwnId(Integer id);
    public List<User> findAllByAge(Integer age);
    public List<User> findAllByAgeBetween(Integer age1, Integer age2);
    @Query(value = "select * from user where user_name like %?1% or full_name like %?1%", nativeQuery = true)
    public List<User> findAllByName(String name);
    public List<User> findAllByCountry(String country);
    public List<User> findAllByCityLike(String city);
    public List<User> findAllByProfession(String profession);
    public List<User> findAllByGender(Gender gender);

    @Query(value = "SELECT * FROM user WHERE age like %?1% OR user_name like %?1% OR country like %?1% " +
            "OR full_name like %?1% OR city like %?1% OR area_code like %?1% " +
            "OR profession like %?1%", nativeQuery = true)
    public List<User> searchForAll(String keywords);

    public User findAllByUserNameAndPassword(String userName, String password);

    @Query(value = "SELECT id FROM user ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public Integer findMaximumId();

    public boolean existsByUserName(String userName);

}
