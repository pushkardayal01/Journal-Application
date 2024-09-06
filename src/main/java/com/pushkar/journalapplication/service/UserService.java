package com.pushkar.journalapplication.service;


import com.pushkar.journalapplication.entity.JournalEntry;
import com.pushkar.journalapplication.entity.User;
import com.pushkar.journalapplication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createuser(User user) throws Exception{
        try{
            userRepository.save(user);
        }
        catch(Exception e){
            throw new Exception("Username allready found!");
        }

    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(ObjectId id){
        Optional<User> entry = userRepository.findById(id);

        if(entry.isEmpty()){
            return null;
        }
        return entry.get();
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void updateUser(User user){
        Optional<User> entry = userRepository.findById(user.getId());
        if(entry.isEmpty()){
            userRepository.save(user);
        }

        User olduser = entry.get();
        if(user.getUsername()!=null){
            olduser.setUsername(user.getUsername());
        }

        if(user.getPassword()!=null){
            olduser.setPassword(user.getPassword());
        }

        userRepository.save(olduser);



    }

    public User getByUserName(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public void chagepassword(String username,String password){
        User user = userRepository.findByUsername(username);
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    public void addjournalEntry(String username,JournalEntry entry){
        User user = userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        journalEntries.add(entry);
        userRepository.save(user);
    }

    public void deleteJournalEntry(String username,JournalEntry entry){
        User user = userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        journalEntries.remove(entry);
        userRepository.save(user);
    }

    public void updateJournalEntry(String username,JournalEntry entry){
        User user = userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        for(int i=0;i<journalEntries.size();i++){
            if(journalEntries.get(i).getId()==entry.getId()){
                journalEntries.remove(journalEntries.get(i));
            }
        }
        userRepository.save(user);
    }

    public List<JournalEntry> getAllJournal(String username){
        User user = userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        return journalEntries;
    }
}
