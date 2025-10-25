package com.example.Spring.revesion._5.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {
    private ObjectId id;
    private String username;
    private String name;
    private int age;

}
