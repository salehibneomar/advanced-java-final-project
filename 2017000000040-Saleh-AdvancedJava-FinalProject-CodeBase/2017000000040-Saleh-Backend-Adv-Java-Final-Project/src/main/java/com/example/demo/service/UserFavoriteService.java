package com.example.demo.service;

import com.example.demo.Exception.ResourceAlreadyExistsException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.UserFavorite;
import com.example.demo.repository.UserFavouriteRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserFavoriteService {
    private UserFavouriteRepository userFavouriteRepository;
    private UserRepository userRepository;

    @Autowired
    public UserFavoriteService(UserFavouriteRepository userFavouriteRepository, UserRepository userRepository) {
        this.userFavouriteRepository = userFavouriteRepository;
        this.userRepository = userRepository;
    }

//Methods
    public List<UserFavorite> getUserFavouritesList(){
        List<UserFavorite> userFavoriteList = new ArrayList<>();
        userFavouriteRepository.findAll().forEach(userFavoriteList::add);
        return userFavoriteList;
    }

    public List<UserFavorite> getUserFavouritesByFavouriteMakerId(Integer favouriteMakerId) throws ResourceNotFoundException {
        if(userFavouriteRepository.existsByFavouriteMakerId(favouriteMakerId)){
            List<UserFavorite> userFavoriteList = new ArrayList<>();
            userFavouriteRepository.findAllByFavouriteMakerId(favouriteMakerId).forEach(userFavoriteList::add);
            return userFavoriteList;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public List<UserFavorite> getAllFavouritesByDateAddedBetween(LocalDate from, LocalDate to){
        List<UserFavorite> userFavoriteList = new ArrayList<>();
        userFavouriteRepository.findAllByDateAddedBetween(from, to).forEach(userFavoriteList::add);
        return userFavoriteList;
    }

    public Integer getCountOfFavouritesByFavouriteMakerId(Integer id) throws ResourceNotFoundException{
        if(userFavouriteRepository.existsByFavouriteMakerId(id)){
            Integer count = userFavouriteRepository.countAllByFavouriteMakerId(id);
            return count;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public String removeSingleFavourite(Integer favouriteId, Integer makerId) throws ResourceNotFoundException{
        if(userFavouriteRepository.existsById(favouriteId) && userFavouriteRepository.existsByFavouriteMakerId(makerId)){
            UserFavorite removedFavorite = userFavouriteRepository.findById(favouriteId).get();
            userFavouriteRepository.delete(removedFavorite);
            return removedFavorite.getFavouriteUser().getUserName();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public String addAFavourite(UserFavorite userFavorite) throws Exception{
        if(userFavouriteRepository.existsByFavouriteMakerId(userFavorite.getFavouriteMaker().getId()) && userFavouriteRepository.existsByFavouriteUserId(userFavorite.getFavouriteUser().getId())){
            throw new ResourceAlreadyExistsException();
        }
        else if(userRepository.existsById(userFavorite.getFavouriteMaker().getId())){
            userFavouriteRepository.save(userFavorite);
            return userFavorite.getFavouriteUser().getUserName();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public boolean deleteAllByFavouriteMakerId(Integer id) throws ResourceNotFoundException{
        if(userFavouriteRepository.existsByFavouriteMakerId(id)){
            userFavouriteRepository.deleteAllByFavouriteMaker(userRepository.findById(id).get());
            return true;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public Integer getMaxId(){
        if(userFavouriteRepository.findMaximumId()==null) return 0;
        else return userFavouriteRepository.findMaximumId();
    }

}
