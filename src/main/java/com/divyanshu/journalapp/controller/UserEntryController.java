package com.divyanshu.journalapp.controller;

import com.divyanshu.journalapp.entity.User;
import com.divyanshu.journalapp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    UserEntryService userEntryService;

    @GetMapping("{userName}")
    public ResponseEntity<?> findUserByUserName(@PathVariable String userName) {
        try {
            User newUser = userEntryService.findUserByUserName(userName);
            return new ResponseEntity<>(newUser, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {

                User newUser = userEntryService.saveUserEntry(user);
                return new ResponseEntity<>(user, HttpStatus.CREATED);



        }
        catch(Exception e) {
            String errorMessage = "Error creating user: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);



        }

    }


}
