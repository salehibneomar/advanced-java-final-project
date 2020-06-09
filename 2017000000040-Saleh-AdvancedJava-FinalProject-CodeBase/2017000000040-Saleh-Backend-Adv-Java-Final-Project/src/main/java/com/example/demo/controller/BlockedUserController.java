package com.example.demo.controller;

import com.example.demo.model.BlockedUser;
import com.example.demo.service.BlockedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BlockedUserController {

    BlockedUserService blockedUserService;

    @Autowired
    public BlockedUserController(BlockedUserService blockedUserService) {
        this.blockedUserService = blockedUserService;
    }

    @GetMapping("api/blocked-users")
    public ResponseEntity<List<BlockedUser> > getBlockedUserList(){
        List<BlockedUser> blockedUserList = blockedUserService.getBlockedUserList();
        if(blockedUserList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(blockedUserList);
        }
    }

    @GetMapping("api/blocked-users-by-blocker-id/{id}")
    public ResponseEntity<List<BlockedUser> > getBlockerUserListByBlockerId(@PathVariable Integer id){
        try{
            List<BlockedUser> blockedUserList = blockedUserService.getBlockerUserListByBlockerId(id);
            return ResponseEntity.ok(blockedUserList);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/blocked-users-by-date-between")
    public ResponseEntity<List<BlockedUser> > getBlockerUserListBetweenDates(@RequestParam  String from, @RequestParam  String to){
        List<BlockedUser> blockedUserList = blockedUserService.getBlockerUserListBetweenDates(LocalDate.parse(from), LocalDate.parse(to));
        if(blockedUserList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(blockedUserList);
        }
    }

    @GetMapping("api/blocked-users-count-by-blocker-id/{id}")
    public ResponseEntity<Integer> getCountOfBlockedPersonByBlockerId(@PathVariable Integer id){
        try{
            Integer count = blockedUserService.getCountOfBlockedPersonByBlockerId(id);
            return ResponseEntity.ok(count);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("api/blocked-user-removal")
    public ResponseEntity<String> removeAUserFromBlockedList(@RequestParam Integer blockId, @RequestParam Integer blockerId){
        try{
            String removedBlockedUserName = blockedUserService.removeAUserFromBlockedList(blockId, blockerId);
            return ResponseEntity.ok(removedBlockedUserName);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/blocked-user-create")
    public ResponseEntity<String>  blockAUser(@RequestBody BlockedUser blockedUser){
        try {
            String blockedUserName = blockedUserService.blockAUser(blockedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(blockedUserName+", has been blocked!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/blocked-users-max-id")
    public ResponseEntity<Integer> getMaxId(){
        return ResponseEntity.ok(blockedUserService.getMaxId());
    }

    @DeleteMapping("api/blocked-user-remove-all-by-blocker-id/{id}")
    public ResponseEntity<Boolean> deleteAllByBlockerUserId(@PathVariable Integer id){
        try{
            boolean status = blockedUserService.deleteAllByBlockerUserId(id);
            return ResponseEntity.ok(status);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
