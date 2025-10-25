package com.example.Spring.revesion._5.Service;

import com.example.Spring.revesion._5.DTOs.UserRequestDTO;
import com.example.Spring.revesion._5.DTOs.UserResponseDTO;
import com.example.Spring.revesion._5.DTOs.UserUpdateRequestDTO;
import com.example.Spring.revesion._5.Model.USER;
import com.example.Spring.revesion._5.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public List<UserResponseDTO> getAllUser(){

        List<USER> all = userRepository.findAll();
        List<UserResponseDTO> dtoList = new ArrayList<>();

        for(USER u :all){
            UserResponseDTO dto = mapper.map(u, UserResponseDTO.class);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public UserResponseDTO getUserFromId(String id){
        try {
            ObjectId objectId = new ObjectId(id);
            USER user = userRepository.findById(objectId).orElse(null);
            if (user==null)
                return null;
//            UserResponseDTO dto = new UserResponseDTO();
//            dto.setUsername(user.getUsername());
//            dto.setAge(user.getAge());
//            dto.setName(user.getName());
//            dto.setId(user.getId().toString());
        //We Will use Model Paper (❁´◡`❁)
            UserResponseDTO dto = mapper.map(user, UserResponseDTO.class);

//            System.out.println(dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public UserResponseDTO createUser(UserRequestDTO dto){
        USER user = mapper.map(dto, USER.class);

        userRepository.save(user);

        return new UserResponseDTO(user.getId().toString(),user.getUsername(), user.getName(), user.getAge());
    }


    public UserResponseDTO updateUserFromUserId(USER user, UserUpdateRequestDTO dto){
        if(user == null){
            throw new IllegalArgumentException("User not found for update");
        }
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setAge(dto.getAge());

        // save will update existing because ID exists
        userRepository.save(user);

        UserResponseDTO responseDTO = mapper.map(user, UserResponseDTO.class);
        return responseDTO;
    }

}
