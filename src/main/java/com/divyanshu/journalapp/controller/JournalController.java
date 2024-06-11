package com.divyanshu.journalapp.controller;

import com.divyanshu.journalapp.entity.JournalEntry;
import com.divyanshu.journalapp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEntries();

    }

    @GetMapping("id/{id}")
    public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getJournalEntryById(id);
    }

    @DeleteMapping("id/{id}")
    public boolean deleteById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        Optional<JournalEntry> old = journalEntryService.getJournalEntryById(id);
        if(old.isPresent()) {
            JournalEntry newEntry = old.get();
            newEntry.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : newEntry.getTitle());
            newEntry.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ? myEntry.getContent() : newEntry.getContent() );
        }


        journalEntryService.saveEntry(myEntry);
        return myEntry;

    }
}
