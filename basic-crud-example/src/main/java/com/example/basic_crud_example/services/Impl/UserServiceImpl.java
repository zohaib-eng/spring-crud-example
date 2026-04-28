package com.example.basic_crud_example.services.Impl;

import com.example.basic_crud_example.datamodel.User;
import com.example.basic_crud_example.datamodel.repo.UserRepo;
import com.example.basic_crud_example.domain.UserDTO;
import com.example.basic_crud_example.domain.inbound.UserDTOIn;
import com.example.basic_crud_example.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepo userRepo;

    public UserDTO findByEmail(UserDTOIn userDTOIn) {
        Optional<User> optionalUser = userRepo.findByEmail(userDTOIn.getEmail());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            return new UserDTO(
                    existingUser.getId().toString(),
                    existingUser.getName(),
                    existingUser.getEmail(),
                    existingUser.getPhoneNumber(),
                    existingUser.getPassword()
            );
        } else {
            User newUser = new User();
            newUser.setName(userDTOIn.getName());
            newUser.setEmail(userDTOIn.getEmail());
            newUser.setPhoneNumber(userDTOIn.getPhoneNumber());
            newUser.setPassword(userDTOIn.getPassword());

            User savedUser = userRepo.save(newUser);

            return new UserDTO(
                    savedUser.getId().toString(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPhoneNumber(),
                    savedUser.getPassword()
            );
        }
    }
}
