package com.divyanshu.journalapp.controller;

import com.divyanshu.journalapp.entity.JournalEntry;
import com.divyanshu.journalapp.entity.User;
import com.divyanshu.journalapp.service.JournalEntryService;
import com.divyanshu.journalapp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;


    @Transactional
    @PostMapping("{userName}")
    public ResponseEntity<?> createEntry(@PathVariable String userName,@RequestBody JournalEntry myEntry) {

        try {

            User userFound = userEntryService.findUserByUserName(userName);

                List<JournalEntry> journalEntry = userFound.getJournalEntries();
                myEntry.setDate(LocalDateTime.now());
                journalEntry.add(myEntry);
                journalEntryService.saveEntry(myEntry);
                userEntryService.saveUserEntry(userFound);
                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);


        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<JournalEntry> all = journalEntryService.getAllEntries();
            return new ResponseEntity<>(all,HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


    }

    @GetMapping("{userName}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String userName) {
        try {
            User userFound = userEntryService.findUserByUserName(userName);
            List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByUserName(userFound);
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);

        }

//
    }


    @Transactional
    @DeleteMapping("{userName}/{journalId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable String userName,@PathVariable ObjectId journalId) {
        try {
            journalEntryService.deleteById(journalId);
            User userFound = userEntryService.findUserByUserName(userName);
            userFound.getJournalEntries().removeIf(x -> x.getId().equals(journalId));
            userEntryService.saveUserEntry(userFound);
            return new ResponseEntity<>("journal deleted", HttpStatus.OK);




        } catch (Exception e) {
            return new ResponseEntity<>("user or journal not found", HttpStatus.BAD_REQUEST);
        }



    }

    @Transactional
    @PutMapping("{userName}/{journalId}")
    public ResponseEntity<?> updateJournalByUserNameAndJournalId(@RequestBody JournalEntry newJournal,@PathVariable String userName,@PathVariable ObjectId journalId) {
        try {
            Optional<JournalEntry> journalFound = journalEntryService.findJournalById(journalId);
            JournalEntry journalFoundNew = journalFound.get();
            journalFoundNew.setContent(newJournal.getContent());
            journalFoundNew.setTitle(newJournal.getTitle());
            journalEntryService.saveEntry(journalFoundNew);

            User userFound = userEntryService.findUserByUserName(userName);
            userFound.getJournalEntries().removeIf(x -> x.getId().equals(journalId));
            userFound.getJournalEntries().add(journalFoundNew);
            userEntryService.saveUserEntry(userFound);
            return new ResponseEntity<>(journalFoundNew, HttpStatus.OK);




        } catch (Exception e) {
            return new ResponseEntity<>("user or journal not found", HttpStatus.BAD_REQUEST);
        }



    }

//
}
