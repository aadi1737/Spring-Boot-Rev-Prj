package com.example.Spring.revesion._5.DTOs;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Username can not be blank.")
    private String username;

    @NotBlank(message = "Name is Mandatory.")
    private String name;

    @NotBlank(message = "Password cant be blank.")
    @Size(min = 6)
    private String  password;

    @Min(value = 1,message = "Age Must be greate than 0")
    private int age;

}
