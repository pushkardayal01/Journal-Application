package com.pushkar.journalapplication.controller;


import com.pushkar.journalapplication.entity.User;
import com.pushkar.journalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createuser(@RequestBody User user) throws Exception {
        try{
            userService.createuser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch(Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<User> updateuser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<User> getUserByUserName(String username){
        User user = userService.getByUserName(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
