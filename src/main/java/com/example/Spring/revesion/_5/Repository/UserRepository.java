package com.example.Spring.revesion._5.Repository;

import com.example.Spring.revesion._5.Model.USER;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<USER,String> {
    USER findByUserId(String id);
}
