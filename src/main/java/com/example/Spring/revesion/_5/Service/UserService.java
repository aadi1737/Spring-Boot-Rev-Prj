package com.example.Spring.revesion._5.Service;

import com.example.Spring.revesion._5.DTOs.UserRequestDTO;
import com.example.Spring.revesion._5.DTOs.UserResponseDTO;
import com.example.Spring.revesion._5.DTOs.UserUpdateRequestDTO;
import com.example.Spring.revesion._5.Model.USER;
import com.example.Spring.revesion._5.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getAllUser(){

        List<USER> all = userRepository.findAll();
        List<UserResponseDTO> dtoList = new ArrayList<>();

        for(USER u :all){
            UserResponseDTO dto = new UserResponseDTO();
            dto.setName(u.getName());
            dto.setUserId(u.getUserId());
            dto.setAge(u.getAge());

            dtoList.add(dto);
        }

        return dtoList;
    }

    public UserResponseDTO getUserFromId(String id){
        try {
            USER user = userRepository.findByUserId(id);
            if (user==null)
                return null;
            UserResponseDTO dto = new UserResponseDTO();
            dto.setUserId(user.getUserId());
            dto.setAge(user.getAge());
            dto.setName(user.getName());

//            System.out.println(dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public UserResponseDTO createUser(UserRequestDTO dto){
        USER user = new USER();
        user.setAge(dto.getAge());
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());

        userRepository.save(user);

        return new UserResponseDTO(user.getUserId(), user.getName(), user.getAge());
    }


    public UserResponseDTO updateUserFromUserId(USER user, UserUpdateRequestDTO dto){
        if(user == null){
            throw new IllegalArgumentException("User not found for update");
        }

        user.setName(dto.getName());
        user.setAge(dto.getAge());

        // save will update existing because ID exists
        userRepository.save(user);

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserId(user.getUserId());
        responseDTO.setName(user.getName());
        responseDTO.setAge(user.getAge());
        return responseDTO;
    }

}
