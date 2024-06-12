package com.divyanshu.journalapp.entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {

    @Id   // mapping as primary key
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
