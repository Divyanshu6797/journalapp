package com.divyanshu.journalapp.service;

import com.divyanshu.journalapp.entity.JournalEntry;
import com.divyanshu.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        Optional<JournalEntry> new1 = journalEntryRepository.findById(id);
        System.out.println(new1);
        return new1;
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }



}
