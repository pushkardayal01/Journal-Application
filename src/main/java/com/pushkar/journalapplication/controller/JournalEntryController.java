package com.pushkar.journalapplication.controller;

import com.pushkar.journalapplication.entity.JournalEntry;
import com.pushkar.journalapplication.service.JournalEntryService;
import com.pushkar.journalapplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping("/check")
    public String check(){
        return "hello";
    }

    @GetMapping("/{username}")
    public List<JournalEntry> getall(@PathVariable String username){
        List<JournalEntry> journalEntries = userService.getAllJournal(username);
        return journalEntries;
    }

    @PostMapping("/{username}")
    @Transactional
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String username) throws Exception {
        try{
            JournalEntry entry= journalEntryService.saveEntry(journalEntry);
            userService.addjournalEntry(username,entry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        catch(Exception e){
            throw new Exception("Bad Request");
        }


    }


    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getbyid(@PathVariable ObjectId myid){
        try{
            JournalEntry entry =journalEntryService.getById(myid);
            return new ResponseEntity<>(entry,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("id/{username}/{myid}")
    @Transactional
    public ResponseEntity<Boolean> deletebyid(@PathVariable ObjectId myid, @PathVariable String username) throws Exception {
        try{
            journalEntryService.deleteById(myid);
            userService.deleteJournalEntry(username,myid);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch(Exception e){
            throw  new Exception("Somethings went wrong");
        }

    }

    @PostMapping("id/{username}/{myid}")
    @Transactional
    public ResponseEntity<JournalEntry> updatejournal(@PathVariable ObjectId myid, @RequestBody JournalEntry journalEntry,@PathVariable String username) throws Exception {
        try{
            JournalEntry entry = journalEntryService.updateEntry(myid,journalEntry);
            userService.updateJournalEntry(username,entry);
            return new ResponseEntity<>(journalEntry,HttpStatus.OK);
        }
        catch(Exception e){
            throw new Exception("Bad Request");
        }


    }




}
