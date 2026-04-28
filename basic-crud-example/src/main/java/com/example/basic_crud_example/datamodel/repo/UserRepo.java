package com.example.basic_crud_example.datamodel.repo;

import com.example.basic_crud_example.datamodel.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);
}
