package com.example.demo.service;

import com.example.demo.Exception.ResourceAlreadyExistsException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.Gender;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//Methods

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public List<User> getAllByFilteringBlockIdAndOwnId(Integer id) throws ResourceNotFoundException{
        if(userRepository.existsById(id)){
            List<User> userList = new ArrayList<>();
            userRepository.findAllByFilteringBlockIdAndOwnId(id).forEach(userList::add);
            return userList;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public List<User> getUsersByAge(Integer age){
        List<User> userList = new ArrayList<>();
        userRepository.findAllByAge(age).forEach(userList::add);
        return userList;
    }

    public List<User> getUsersByAgeBetween(Integer age1, Integer age2){
        List<User> userList = new ArrayList<>();
        userRepository.findAllByAgeBetween(age1, age2).forEach(userList::add);
        return userList;
    }

    public List<User> getUsersByName(String name){
        List<User> userList = new ArrayList<>();
        userRepository.findAllByName(name).forEach(userList::add);
        return userList;
    }

    public List<User> getUsersByCountry(String country){
        List<User> userList = new ArrayList<>();
        userRepository.findAllByCountry(country).forEach(userList::add);
        return userList;
    }

    public List<User> getUsersByCity(String city){
        List<User> userList = new ArrayList<>();
        userRepository.findAllByCityLike(city).forEach(userList::add);
        return userList;
    }

    public List<User> getUsersByProfession(String profession){
        List<User> userList = new ArrayList<>();
        userRepository.findAllByProfession(profession).forEach(userList::add);
        return userList;
    }

    public List<User> getUsersByGender(Gender gender){
        List<User> userList = new ArrayList<>();
        userRepository.findAllByGender(gender).forEach(userList::add);
        return userList;
    }

    public List<User> searchUsers(String keywords){
        List<User> userList = new ArrayList<>();
        userRepository.searchForAll(keywords).forEach(userList::add);
        return userList;
    }

    public User getUserByUserNameAndPassword(String userName, String password) throws ResourceNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findAllByUserNameAndPassword(userName, password));
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public User getUserById(Integer id) throws ResourceNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public boolean checkUserAvailabilityById(Integer id) throws ResourceNotFoundException{
        if(userRepository.existsById(id)){
            return true;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public String deleteUserAccount(Integer id) throws ResourceNotFoundException{
        if(userRepository.existsById(id)){
            Optional<User> optionalUser = userRepository.findById(id);
            User user = optionalUser.get();
            userRepository.delete(user);
            return user.getUserName();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public String updateUserAccount(Integer id, User user) throws ResourceNotFoundException{
        if(userRepository.existsById(id)){
            User updatedUserInformation = user;
            updatedUserInformation.setId(id);
            userRepository.save(updatedUserInformation);
            return user.getUserName();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public String createUser(User user) throws ResourceAlreadyExistsException {
        if(userRepository.existsById(user.getId()) || userRepository.existsByUserName(user.getUserName())){
            throw new ResourceAlreadyExistsException();
        }
        else{
            User createdUser = userRepository.save(user);
            return createdUser.getUserName();
        }
    }

    public Integer getMaxId(){
        if(userRepository.findMaximumId()==null) return 0;
        else return userRepository.findMaximumId();
    }

}
