package com.pushkar.journalapplication.service;

import com.pushkar.journalapplication.entity.JournalEntry;
import com.pushkar.journalapplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryRepository.save(entry);
    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry getById(ObjectId id){
        Optional<JournalEntry> entry = journalEntryRepository.findById(id);

        if(entry.isEmpty()){
            return null;
        }
        return entry.get();
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);


    }

    public void updateEntry(ObjectId id, JournalEntry entry){
        Optional<JournalEntry> journalentry = journalEntryRepository.findById(id);
        if(journalentry.isEmpty()){
            journalEntryRepository.save(entry);
        }
        if(entry.getTitle()!=null){
            journalentry.get().setTitle(entry.getTitle());
        }
        if(entry.getContent()!=null){
            journalentry.get().setContent(entry.getContent());
        }
        journalEntryRepository.save(journalentry.get());


    }

}
