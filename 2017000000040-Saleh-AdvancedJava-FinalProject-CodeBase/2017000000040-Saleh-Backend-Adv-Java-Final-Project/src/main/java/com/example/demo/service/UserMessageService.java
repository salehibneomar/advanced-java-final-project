package com.example.demo.service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.UserMessage;
import com.example.demo.repository.UserMessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMessageService {
    private UserMessageRepository userMessageRepository;
    private UserRepository userRepository;

    @Autowired
    public UserMessageService(UserMessageRepository userMessageRepository, UserRepository userRepository) {
        this.userMessageRepository = userMessageRepository;
        this.userRepository = userRepository;
    }
//Methods

    public List<UserMessage> getAllMessagesBySenderId(Integer id) throws ResourceNotFoundException {
        if(userMessageRepository.existsBySenderId(id)){
            List<UserMessage> userMessageList = new ArrayList<>();
            userMessageRepository.findAllBySenderId(id).forEach(userMessageList::add);
            return userMessageList;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public List<UserMessage> getAllMessagesByRecipientId(Integer id) throws ResourceNotFoundException{
        if(userMessageRepository.existsByRecipientId(id)){
            List<UserMessage> userMessageList = new ArrayList<>();
            userMessageRepository.findAllByRecipientId(id).forEach(userMessageList::add);
            return userMessageList;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public Integer countAllUnseenMessagesByRecipientId(Integer id) throws ResourceNotFoundException{
        if(userMessageRepository.existsByRecipientId(id)){
            Integer count = userMessageRepository.countAllByRecipientIdAndSeenStatusIsFalse(id);
            return count;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public boolean deleteSingleMessage(Integer messageId, Integer senderId) throws ResourceNotFoundException{
        if(userMessageRepository.existsById(messageId) && userMessageRepository.existsBySenderId(senderId)){
            UserMessage userMessage = userMessageRepository.findById(messageId).get();
            userMessageRepository.delete(userMessage);
            return true;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

    public String sendMessage(UserMessage userMessage) throws ResourceNotFoundException{
        if(userRepository.existsById(userMessage.getSender().getId()) && userRepository.existsById(userMessage.getRecipient().getId())){
            userMessageRepository.save(userMessage);
            return userMessage.getRecipient().getUserName();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public Integer getMaxId(){
        if(userMessageRepository.findMaximumId()==null) return 0;
        else return userMessageRepository.findMaximumId();
    }

    public boolean deleteAllByUser(Integer id) throws ResourceNotFoundException{
        if(userMessageRepository.existsBySenderId(id) || userMessageRepository.existsByRecipientId(id)){
            userMessageRepository.deleteAllByUser(userRepository.findById(id).get());
            return true;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }

}
