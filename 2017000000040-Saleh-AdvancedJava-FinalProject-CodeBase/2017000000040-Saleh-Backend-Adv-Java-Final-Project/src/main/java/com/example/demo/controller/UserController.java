package com.example.demo.controller;

import com.example.demo.model.Gender;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//Methods

   @GetMapping("api/user-availability-check-by-id/{id}")
    public ResponseEntity<Boolean> checkUserAvailabilityById(@PathVariable Integer id){
        try{
            Boolean check = userService.checkUserAvailabilityById(id);
            return ResponseEntity.ok(check);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
   }

   @GetMapping("api/users-by-age/{age}")
    public ResponseEntity<List<User> > getUsersByAge(@PathVariable Integer age){
        List<User> userList = userService.getUsersByAge(age);
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
   }

   @GetMapping("api/users-by-age-between")
   public ResponseEntity<List<User> > getUsersByAgeBetween(@RequestParam Integer age1, @RequestParam Integer age2){
       List<User> userList = userService.getUsersByAgeBetween(age1, age2);
       if(userList.isEmpty()){
           return ResponseEntity.noContent().build();
       }
       else{
           return ResponseEntity.ok(userList);
       }
   }

    @GetMapping("api/users-by-name/{name}")
    public ResponseEntity<List<User> > getUsersByName(@PathVariable String name){
        List<User> userList = userService.getUsersByName(name);
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("api/users-by-country/{country}")
    public ResponseEntity<List<User> > getUsersByCountry(@PathVariable String country){
        List<User> userList = userService.getUsersByCountry(country);
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("api/users-by-city/{city}")
    public ResponseEntity<List<User> > getUsersByCity(@PathVariable String city){
        List<User> userList = userService.getUsersByCity(city);
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("api/users-by-profession/{profession}")
    public ResponseEntity<List<User> > getUsersByProfession(@PathVariable String profession){
        List<User> userList = userService.getUsersByProfession(profession);
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("api/users-by-gender/{gender}")
    public ResponseEntity<List<User> > getUsersByCountry(@PathVariable Gender gender){
        List<User> userList = userService.getUsersByGender((Gender)gender);
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("api/users-search/{keyword}")
    public ResponseEntity<List<User> > searchUsers(@PathVariable String keyword){
        List<User> userList = userService.searchUsers(keyword);
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("api/user-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        try{
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/user-by-username-password")
    public ResponseEntity<User> getUserByUserNameAndPassword(@RequestBody Login login){
        try{
            User user = userService.getUserByUserNameAndPassword(login.getUserName(), login.getPassword());
            return ResponseEntity.ok(user);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User> > getAllUsers(){
        List<User> userList = userService.getAllUsers();
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("api/users-by-filtering-blocked-users-and-id/{id}")
    public ResponseEntity<List<User> > getAllByFilteringBlockIdAndOwnId(@PathVariable Integer id){
        try{
            List<User> userList = userService.getAllByFilteringBlockIdAndOwnId(id);
            return ResponseEntity.ok(userList);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("api/user-account-delete-by-id/{id}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable Integer id){
        try{
            String userName = userService.deleteUserAccount(id);
            return ResponseEntity.ok(userName+", your account has been deleted!");
        }
        catch (Exception e){
           return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("api/user-account-update-by-id/{id}")
    public ResponseEntity<String> updateUserAccount(@PathVariable Integer id, @RequestBody User user){
        try{
            String userName = userService.updateUserAccount(id, user);
            return ResponseEntity.ok(userName+", your account has been updated!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/user-create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            String userName = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userName+", your account has been created!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while creating");
        }
    }

    @GetMapping("api/user-max-id")
    public ResponseEntity<Integer> getMaxId(){
        return ResponseEntity.ok(userService.getMaxId());
    }
}
