package com.example.basic_crud_example.services.Impl;

import com.example.basic_crud_example.datamodel.User;
import com.example.basic_crud_example.datamodel.repo.UserRepo;
import com.example.basic_crud_example.domain.UserDTO;
import com.example.basic_crud_example.domain.inbound.UserDTOIn;
import com.example.basic_crud_example.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepo userRepo;

    public UserDTO createByEmail(UserDTOIn userDTOIn) {
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

    @Override
    public UserDTO updateByEmail(String email, UserDTOIn userDTOIn) {
        User existingUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        existingUser.setName(userDTOIn.getName());
        existingUser.setEmail(userDTOIn.getEmail()); // optional (careful)
        existingUser.setPhoneNumber(userDTOIn.getPhoneNumber());
        existingUser.setPassword(userDTOIn.getPassword());

        User updatedUser = userRepo.save(existingUser);

        return new UserDTO(
                updatedUser.getId().toString(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getPhoneNumber(),
                updatedUser.getPassword()
        );
    }

    @Override
    public UserDTO findByEmail(String email) {
        User existingUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return new UserDTO(
                existingUser.getId().toString(),
                existingUser.getName(),
                existingUser.getEmail(),
                existingUser.getPhoneNumber(),
                existingUser.getPassword()
        );
    }

    @Override
    public UserDTO deleteByEmail(String email) {
        User existingUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        userRepo.delete(existingUser);
        return new UserDTO(
                existingUser.getId().toString(),
                existingUser.getName(),
                existingUser.getEmail(),
                existingUser.getPhoneNumber(),
                existingUser.getPassword()
        );
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> users = new ArrayList<>();

        for (User user : userRepo.findAll()) {
            users.add(
                    new UserDTO(
                            user.getId().toString(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getPassword()
                    )
            );
        }

        return users;
    }
}
