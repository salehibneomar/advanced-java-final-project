package com.example.demo.controller;

import com.example.demo.model.UserMessage;
import com.example.demo.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserMessageController {
    private UserMessageService userMessageService;

    @Autowired
    public UserMessageController(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }

    @GetMapping("api/user-messages-by-sender-id/{id}")
    public ResponseEntity<List<UserMessage> > getAllMessagesBySenderId(@PathVariable Integer id){
        try{
            List<UserMessage> userMessageList = userMessageService.getAllMessagesBySenderId(id);
            return ResponseEntity.ok(userMessageList);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/user-messages-by-recipient-id/{id}")
    public ResponseEntity<List<UserMessage> > getAllMessagesByRecipientId(@PathVariable Integer id){
        try{
            List<UserMessage> userMessageList = userMessageService.getAllMessagesByRecipientId(id);
            return ResponseEntity.ok(userMessageList);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/user-unseen-messages-count-by-recipient-id/{id}")
    public ResponseEntity<Integer> countAllUnseenMessagesByRecipientId(@PathVariable Integer id){
        try{
            Integer count = userMessageService.countAllUnseenMessagesByRecipientId(id);
            return ResponseEntity.ok(count);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("api/user-message-delete")
    public ResponseEntity<Boolean> deleteSingleMessage(@RequestParam Integer messageId, @RequestParam Integer userId){
        try{
            userMessageService.deleteSingleMessage(messageId, userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("api/user-delete-all-by-user/{id}")
    public ResponseEntity<Boolean> deleteAllByUser(@PathVariable Integer id){
        try{
            boolean status = userMessageService.deleteAllByUser(id);
            return ResponseEntity.ok(status);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/user-message-send")
    public ResponseEntity<String> sendMessage(UserMessage userMessage){
        try {
            String message = userMessageService.sendMessage(userMessage);
            return ResponseEntity.status(HttpStatus.CREATED).body("Message delivered to, "+message);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/user-messages-max-id")
    public ResponseEntity<Integer> getMaxId(){
        return ResponseEntity.ok(userMessageService.getMaxId());
    }
}
