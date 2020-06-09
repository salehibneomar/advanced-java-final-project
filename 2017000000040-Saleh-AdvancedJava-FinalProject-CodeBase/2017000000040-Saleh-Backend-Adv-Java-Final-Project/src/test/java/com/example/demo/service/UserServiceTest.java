package com.example.demo.service;

import com.example.demo.model.Gender;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    UserService userService;

    @Autowired
    protected UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    protected void getAllUser(){
        userService.getAllUsers().stream().forEach(System.out::println);
    }

    @Test
    protected void getAllByFilteringBlockIdAndOwnId(){
        try{
            userService.getAllByFilteringBlockIdAndOwnId(1).stream().forEach(System.out::println);
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void getUsersByAge(){
        try{
            List<User> userList = userService.getUsersByAge(30);
            if (userList.isEmpty()){
                System.out.println("No user found!");
            }
            else{
                userList.stream().forEach(System.out::println);
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void getUsersByAgeBetween(){
        try {
            List<User> userList = userService.getUsersByAgeBetween(10,30);
            if (userList.isEmpty()){
                System.out.println("No user found!");
            }
            else{
                userList.stream().forEach(System.out::println);
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void getUserById(){
        try{
            System.out.println(userService.getUserById(1));
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void checkUserAvailabilityById(){
        try{
            boolean status = userService.checkUserAvailabilityById(2);
            System.out.println(status);
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void getUserByUserNameAndPassword(){
        try {
            System.out.println(userService.getUserByUserNameAndPassword("Jane", "123456"));
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void searchForAll(){
        try {
            List<User> userList = userService.searchUsers("Abdul");
            if (userList.isEmpty()){
                System.out.println("No user found!");
            }
            else{
                userList.stream().forEach(System.out::println);
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void createUser(){
        try{
            User user = new User(userService.getMaxId()+1,"Test", "123test", "Test Acc", 23, Gender.Male, "Bangladesh", "Dhaka", "1234", "Test Account!", "Testing", "img/male.png");
            String insertedUserName = userService.createUser(user);
            assertEquals(user.getUserName(), insertedUserName);
            System.out.println(insertedUserName+", your account has been created!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void updateUserAccount(){
        try{
            User user = new User(8,"Dia", "d11ia22", "Dia Khan", 23, Gender.Female, "Bangladesh", "Dhaka", "1253", "I love designing!", "Designer", "img/dddd.jpg");
            String updatedUserName = userService.updateUserAccount(8, user);
            assertEquals(user.getUserName(), updatedUserName);
            System.out.println(updatedUserName+", your account has been updated!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void deleteUserAccount(){
        try{
            String deletedUser = userService.deleteUserAccount(2);
            System.out.println(deletedUser+", your account has been deleted!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void getUsersByGender(){
        try{
            List<User> userList = userService.getUsersByGender(Gender.Female);
            if (userList.isEmpty()){
                System.out.println("No user found!");
            }
            else{
                userList.stream().forEach(System.out::println);
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

}
