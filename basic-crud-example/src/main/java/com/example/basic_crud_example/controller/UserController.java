package com.example.basic_crud_example.controller;

import com.example.basic_crud_example.common.ApiResponse;
import com.example.basic_crud_example.domain.UserDTO;
import com.example.basic_crud_example.domain.inbound.UserDTOIn;
import com.example.basic_crud_example.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDTO> addAddress(@RequestBody @Valid UserDTOIn userDTOIn) {
        UserDTO user = userService.createByEmail(userDTOIn);
        return ApiResponse.success("User created successfully", user);
    }

    @PutMapping("/update/{email}")
    public ApiResponse<UserDTO> updateUserByEmail(
            @PathVariable String email,
            @RequestBody @Valid UserDTOIn userDTOIn) {

        UserDTO user = userService.updateByEmail(email, userDTOIn);
        return ApiResponse.success("User updated successfully", user);
    }

    @GetMapping("/get/{email}")
    public ApiResponse<UserDTO> getUserByEmail(
            @PathVariable String email) {
        UserDTO user = userService.findByEmail(email);
        return ApiResponse.success("User fetched successfully", user);
    }

    @DeleteMapping("/delete/{email}")
    public ApiResponse<UserDTO> deleteUserByEmail(
            @PathVariable String email) {
        UserDTO user = userService.deleteByEmail(email);
        return ApiResponse.success("User delete successfully", user);
    }

    @GetMapping("/all")
    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> user = userService.findAll();
        return ApiResponse.success("List fetched successfully", user);
    }
}
