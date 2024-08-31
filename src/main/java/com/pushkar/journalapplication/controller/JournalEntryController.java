package com.pushkar.journalapplication.controller;

import com.pushkar.journalapplication.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalentries = new HashMap<>();

    @GetMapping("/check")
    public String check(){
        return "hello";
    }

    @GetMapping
    public List<JournalEntry> getall(){
        return new ArrayList<>(journalentries.values());
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
        return journalentries.put(journalEntry.getId(),journalEntry);
    }


    @GetMapping("id/{myid}")
    public JournalEntry getbyid(@PathVariable Long myid){
        return journalentries.get(myid);
    }


    @DeleteMapping("id/{myid}")
    public JournalEntry deletebyid(@PathVariable Long myid){
        return journalentries.remove(myid);

    }

    @PostMapping("id/{myid}")
    public JournalEntry updatejournal(@PathVariable Long myid, @RequestBody JournalEntry journalEntry){
        return journalentries.put(myid,journalEntry);
    }




}
