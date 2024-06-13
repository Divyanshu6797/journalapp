package com.divyanshu.journalapp.service;

import com.divyanshu.journalapp.entity.JournalEntry;
import com.divyanshu.journalapp.entity.User;
import com.divyanshu.journalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Component
public class UserEntryService {

    @Autowired
    UserRepository userRepository;


    public User saveUserEntry(User userEntry) {
        return userRepository.save(userEntry);
    }

   public User findUserByUserName(String userName) throws Exception {
        try {
            return userRepository.findByUserName(userName);

        }
        catch (Exception e) {
            throw new Exception(e);

        }


   }





}
