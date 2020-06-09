package com.example.demo.controller;

import com.example.demo.model.UserFavorite;
import com.example.demo.service.UserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UserFavouriteController {
    private UserFavoriteService userFavoriteService;

    @Autowired
    public UserFavouriteController(UserFavoriteService userFavoriteService) {
        this.userFavoriteService = userFavoriteService;
    }

    @GetMapping("api/user-favourites")
    public ResponseEntity<List<UserFavorite> > getUserFavouritesList(){
        List<UserFavorite> userFavoriteList = userFavoriteService.getUserFavouritesList();
        if(userFavoriteList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userFavoriteList);
        }
    }

    @GetMapping("api/user-favourites-by-favourite-maker-id/{id}")
    public ResponseEntity<List<UserFavorite> > getUserFavouritesByFavouriteMakerId(@PathVariable Integer id){
        try{
            List<UserFavorite> userFavoriteList = userFavoriteService.getUserFavouritesByFavouriteMakerId(id);
            return ResponseEntity.ok(userFavoriteList);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/user-favourites-by-date-between")
    public ResponseEntity<List<UserFavorite> > getAllFavouritesByDateAddedBetween(@RequestParam String from, @RequestParam String to){
        List<UserFavorite> userFavoriteList = userFavoriteService.getAllFavouritesByDateAddedBetween(LocalDate.parse(from), LocalDate.parse(to));
        if(userFavoriteList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userFavoriteList);
        }
    }

    @GetMapping("api/user-favourites-count-by-favourite-maker-id/{id}")
    public ResponseEntity<Integer> getCountOfFavouritesByFavouriteMakerId(@PathVariable Integer id){
        try{
            Integer count = userFavoriteService.getCountOfFavouritesByFavouriteMakerId(id);
            return ResponseEntity.ok(count);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("api/user-favourite-removal")
    public ResponseEntity<String> removeSingleFavourite(@RequestParam Integer favouriteId, @RequestParam Integer makerId){
        try{
            String removedUserFavoriteUserName = userFavoriteService.removeSingleFavourite(favouriteId, makerId);
            return ResponseEntity.ok(removedUserFavoriteUserName);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/user-favourite-create")
    public ResponseEntity<String> addAFavourite(@RequestBody UserFavorite userFavorite){
        try {
            String favouriteUserName = userFavoriteService.addAFavourite(userFavorite);
            return ResponseEntity.status(HttpStatus.CREATED).body(favouriteUserName);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("api/user-favourite-remove-all-by-favourite-maker-id/{id}")
    public ResponseEntity<Boolean> deleteAllByFavouriteMakerId(@PathVariable Integer id){
        try{
            boolean status = userFavoriteService.deleteAllByFavouriteMakerId(id);
            return ResponseEntity.ok(status);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/user-favourites-max-id")
    public ResponseEntity<Integer> getMaxId(){
        return ResponseEntity.ok(userFavoriteService.getMaxId());
    }

}
