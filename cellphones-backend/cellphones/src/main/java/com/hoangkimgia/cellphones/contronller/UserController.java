package com.hoangkimgia.cellphones.contronller;

import com.hoangkimgia.cellphones.dto.reponse.ApiResponse;
import com.hoangkimgia.cellphones.entity.User;
import com.hoangkimgia.cellphones.exception.UserAlreadyExistsException;
import com.hoangkimgia.cellphones.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserServiceImpl userServiceImpl;
    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userServiceImpl.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Registration successful!");
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<>(userServiceImpl.getUsers(),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        try{
            userServiceImpl.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserId(@PathVariable("userId")String userId){
        try{
            User theUser= userServiceImpl.getUserId(userId);
            return ResponseEntity.ok(theUser);
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
