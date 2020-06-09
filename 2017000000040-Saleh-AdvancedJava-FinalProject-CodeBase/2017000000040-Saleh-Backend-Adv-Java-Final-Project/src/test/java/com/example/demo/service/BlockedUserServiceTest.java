package com.example.demo.service;

import com.example.demo.model.BlockedUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class BlockedUserServiceTest {

    private BlockedUserService blockedUserService;
    private UserService userService;

    @Autowired
    public BlockedUserServiceTest(BlockedUserService blockedUserService, UserService userService) {
        this.blockedUserService = blockedUserService;
        this.userService = userService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    protected void getBlockerUserListByBlockerId(){
        try{
            List<BlockedUser> blockedUserList= blockedUserService.getBlockerUserListByBlockerId(11);
            if(blockedUserList.isEmpty()){
                System.out.println("Empty list!");
            }
            else{
                blockedUserList.stream().forEach(System.out::println);
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void blockAUser(){
        try{
            Integer id=blockedUserService.getMaxId()+1;
            BlockedUser blockedUser = new BlockedUser(id, LocalDate.now(), userService.getUserById(1), userService.getUserById(2));
            String blockedUserName = blockedUserService.blockAUser(blockedUser);
            System.out.println(blockedUserName+", has been blocked!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void removeAUserFromBlockedList(){
        try{
            String blockedUserUserName = blockedUserService.removeAUserFromBlockedList(1, 1);
            System.out.println(blockedUserUserName+", has been removed from your blocked list!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void deleteAllByBlockerUserId(){
        try{
            boolean status = blockedUserService.deleteAllByBlockerUserId(1);
            System.out.println(status);
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }


}
