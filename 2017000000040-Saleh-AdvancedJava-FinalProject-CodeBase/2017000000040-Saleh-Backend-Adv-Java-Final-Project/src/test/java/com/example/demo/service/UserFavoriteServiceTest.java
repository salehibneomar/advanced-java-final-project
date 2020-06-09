package com.example.demo.service;

import com.example.demo.model.UserFavorite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class UserFavoriteServiceTest {

    private UserService userService;
    private UserFavoriteService userFavoriteService;

    @Autowired
    public UserFavoriteServiceTest(UserService userService, UserFavoriteService userFavoriteService) {
        this.userService = userService;
        this.userFavoriteService = userFavoriteService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    protected void findAllByFavouriteMakerId(){
        try {
            List<UserFavorite> userFavoriteList = userFavoriteService.getUserFavouritesByFavouriteMakerId(1);
            if(userFavoriteList.isEmpty()){
                System.out.println("Empty List!");
            }
            else{
                userFavoriteList.stream().forEach(System.out::println);
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void removeSingleFavourite(){
        try{
            String removedFavouriteUserName = userFavoriteService.removeSingleFavourite(1, 1);
            System.out.println(removedFavouriteUserName+", has been removed from your favourite list!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void addAFavourite(){
        try{
            UserFavorite userFavorite = new UserFavorite(userFavoriteService.getMaxId()+1, LocalDate.now(), userService.getUserById(1), userService.getUserById(4));
            String addedFavouriteUserName = userFavoriteService.addAFavourite(userFavorite);
            System.out.println(addedFavouriteUserName+", has been added to your favourite list!");
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    @Test
    protected void deleteAllByFavouriteMakerId(){
        try {
            boolean status = userFavoriteService.deleteAllByFavouriteMakerId(1);
            System.out.println(status);
        }
        catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

}
