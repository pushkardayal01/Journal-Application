package com.pushkar.journalapplication.controller;

import com.pushkar.journalapplication.entity.JournalEntry;
import com.pushkar.journalapplication.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;


    @GetMapping("/check")
    public String check(){
        return "hello";
    }

    @GetMapping
    public List<JournalEntry> getall(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry){
        journalEntryService.saveEntry(journalEntry);
        return true;
    }


    @GetMapping("id/{myid}")
    public JournalEntry getbyid(@PathVariable ObjectId myid){

        return journalEntryService.getById(myid);
    }


    @DeleteMapping("id/{myid}")
    public boolean deletebyid(@PathVariable ObjectId myid){
        journalEntryService.deleteById(myid);
        return true;

    }

    @PostMapping("id/{myid}")
    public JournalEntry updatejournal(@PathVariable ObjectId myid, @RequestBody JournalEntry journalEntry){
        journalEntryService.updateEntry(myid,journalEntry);
        return journalEntry;
    }




}
