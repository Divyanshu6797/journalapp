package com.divyanshu.journalapp.controller;

import com.divyanshu.journalapp.entity.JournalEntry;
import com.divyanshu.journalapp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntryService journalEntryService;



    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);

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

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntry> foundEntry = journalEntryService.getJournalEntryById(id);
        if(foundEntry.isPresent()) {
            return new ResponseEntity<>(foundEntry.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//
    }

    @DeleteMapping("id/{id}")
    public boolean deleteById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        Optional<JournalEntry> old = journalEntryService.getJournalEntryById(id);
        JournalEntry newOne = old.get();
        if(old.isPresent()) {
            JournalEntry newEntry = old.get();

            newEntry.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : newEntry.getTitle());
            newEntry.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ? myEntry.getContent() : newEntry.getContent() );
        }


        journalEntryService.saveEntry(newOne);
        return newOne;

    }
}
