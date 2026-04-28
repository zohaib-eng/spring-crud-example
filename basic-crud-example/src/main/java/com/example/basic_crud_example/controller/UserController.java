package com.example.basic_crud_example.controller;

import com.example.basic_crud_example.domain.UserDTO;
import com.example.basic_crud_example.domain.inbound.UserDTOIn;
import com.example.basic_crud_example.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addAddress(@RequestBody @Valid UserDTOIn userDTOIn) {
        return userService.findByEmail(userDTOIn);
    }
}
