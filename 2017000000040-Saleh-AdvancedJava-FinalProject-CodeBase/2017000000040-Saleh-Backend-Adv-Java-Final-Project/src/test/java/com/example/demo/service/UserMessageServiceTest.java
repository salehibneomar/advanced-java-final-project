package com.example.demo.service;

import com.example.demo.model.UserMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class UserMessageServiceTest {

    private UserService userService;
    private UserMessageService userMessageService;

    @Autowired
    public UserMessageServiceTest(UserMessageService userMessageService, UserService userService) {
        this.userMessageService = userMessageService;
        this.userService = userService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    protected void getAllMessagesBySenderId(){
        try {
            List<UserMessage> userMessageList = userMessageService.getAllMessagesBySenderId(1);
            if(userMessageList.isEmpty()){
                System.out.println("No messages!");
            }
            else{
                userMessageList.stream().forEach(System.out::println);
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void countAllUnseenMessagesByRecipientId(){
        try{
            Integer count = userMessageService.countAllUnseenMessagesByRecipientId(1);
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void deleteSingleMessage(){
        try{
            boolean status = userMessageService.deleteSingleMessage(1, 1);
            if (status) System.out.println("Message has been deleted!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void sendMessage(){
        try{
            UserMessage userMessage = new UserMessage(userMessageService.getMaxId()+1, LocalDate.now(), "Hello", false, userService.getUserById(1), userService.getUserById(7));
            String message = userMessageService.sendMessage(userMessage);
            System.out.println(message);
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void getMaxId(){
        System.out.println(userMessageService.getMaxId());
    }

    @Test void deleteAllByUser(){
        try{
            boolean status = userMessageService.deleteAllByUser(1);
            System.out.println(status);
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }
}
