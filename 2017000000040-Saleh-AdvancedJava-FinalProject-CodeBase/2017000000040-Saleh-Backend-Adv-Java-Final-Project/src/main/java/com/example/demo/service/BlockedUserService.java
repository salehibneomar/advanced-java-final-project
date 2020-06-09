package com.example.demo.service;

import com.example.demo.Exception.ResourceAlreadyExistsException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.BlockedUser;
import com.example.demo.repository.BlockedUserRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlockedUserService {
    private BlockedUserRepository blockedUserRepository;
    private UserRepository userRepository;

    @Autowired
    public BlockedUserService(BlockedUserRepository blockedUserRepository, UserRepository userRepository) {
        this.blockedUserRepository = blockedUserRepository;
        this.userRepository = userRepository;
    }

//Methods
    public List<BlockedUser> getBlockedUserList(){
        List<BlockedUser> blockedUserList = new ArrayList<>();
        blockedUserRepository.findAll().forEach(blockedUserList::add);
        return blockedUserList;
    }

    public List<BlockedUser> getBlockerUserListBetweenDates(LocalDate from, LocalDate to){
        List<BlockedUser> blockedUserList = new ArrayList<>();
        blockedUserRepository.findAllByDateBlockedBetween(from,to).forEach(blockedUserList::add);
        return blockedUserList;
    }

    public List<BlockedUser> getBlockerUserListByBlockerId(Integer id) throws ResourceNotFoundException{
        if(blockedUserRepository.existsByBlockerUserId(id)){
            List<BlockedUser> blockedUserList = new ArrayList<>();
            blockedUserRepository.findAllByBlockerUserId(id).forEach(blockedUserList::add);
            return blockedUserList;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public Integer getCountOfBlockedPersonByBlockerId(Integer id) throws ResourceNotFoundException{
        if(blockedUserRepository.existsByBlockerUserId(id)){
            Integer count = blockedUserRepository.countAllByBlockerUserId(id);
            return count;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public String removeAUserFromBlockedList(Integer blockId, Integer blockerId) throws ResourceNotFoundException{
        if(blockedUserRepository.existsById(blockId) && blockedUserRepository.existsByBlockerUserId(blockerId)){
            BlockedUser blockedUser = blockedUserRepository.findById(blockId).get();
            blockedUserRepository.delete(blockedUser);
            return blockedUser.getBlockedUser().getUserName();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public String blockAUser(BlockedUser blockedUser) throws Exception{
        if(blockedUserRepository.existsByBlockedUserId(blockedUser.getBlockedUser().getId()) && blockedUserRepository.existsByBlockerUserId(blockedUser.getBlockerUser().getId())){
            throw new ResourceAlreadyExistsException();
        }
        else if(userRepository.existsById(blockedUser.getBlockerUser().getId())){
            blockedUserRepository.save(blockedUser);
            return blockedUser.getBlockedUser().getUserName();
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public boolean deleteAllByBlockerUserId(Integer id) throws ResourceNotFoundException{
        if(blockedUserRepository.existsByBlockerUserId(id)){
            blockedUserRepository.deleteAllByBlockerUser(userRepository.findById(id).get());
            return true;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public Integer getMaxId(){
        if(blockedUserRepository.findMaximumId()==null) return 0;
        else return blockedUserRepository.findMaximumId();
    }

}
