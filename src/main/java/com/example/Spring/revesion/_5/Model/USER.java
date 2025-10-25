package com.example.Spring.revesion._5.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "User-Rev-2025")
public class USER {

    private String userId;
    private String name;
    private String  password;
    private int age;

}
