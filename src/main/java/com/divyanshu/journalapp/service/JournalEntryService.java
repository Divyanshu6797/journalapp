package com.divyanshu.journalapp.service;

import com.divyanshu.journalapp.entity.JournalEntry;
import com.divyanshu.journalapp.entity.User;
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

    public List<JournalEntry> getJournalEntriesByUserName(User user) {

        List<JournalEntry> journalEntries = user.getJournalEntries();
        return journalEntries;
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
    public Optional<JournalEntry> findJournalById(ObjectId journalId) {
        return journalEntryRepository.findById(journalId);
    }



}
