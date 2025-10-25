package com.example.Spring.revesion._5.Controller;

import com.example.Spring.revesion._5.DTOs.UserRequestDTO;
import com.example.Spring.revesion._5.DTOs.UserResponseDTO;
import com.example.Spring.revesion._5.DTOs.UserUpdateRequestDTO;
import com.example.Spring.revesion._5.Model.USER;
import com.example.Spring.revesion._5.Repository.UserRepository;
import com.example.Spring.revesion._5.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/get-all")
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){

        List<UserResponseDTO> allUser = userService.getAllUser();

        if ((allUser.isEmpty()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());

        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){

        UserResponseDTO userFromId = userService.getUserFromId(id);
//        System.out.println(userFromId);
        if(userFromId==null)
            return new ResponseEntity<>("Can't find User", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(userFromId);

    }


    @PostMapping("/add-user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto){
        UserResponseDTO user = userService.createUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }



    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequestDTO dto){
        ObjectId objectId = new ObjectId(String.valueOf(dto.getId()));
        USER user = userRepository.findById(objectId).orElse(null);
        if(user==null)
            return new ResponseEntity<>("can't Find User with this "+dto.getId()+"User Id",HttpStatus.NOT_FOUND);
        else {
            UserResponseDTO responseDTO = userService.updateUserFromUserId(user, dto);
            return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/del-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        try {
            ObjectId objectId = new ObjectId(id);
            userRepository.deleteById(objectId);
            return new ResponseEntity<>("Successfully Deleted From DB!",HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
