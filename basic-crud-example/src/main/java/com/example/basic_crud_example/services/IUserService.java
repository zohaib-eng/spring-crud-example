package com.example.basic_crud_example.services;

import com.example.basic_crud_example.domain.UserDTO;
import com.example.basic_crud_example.domain.inbound.UserDTOIn;

import java.util.List;

public interface IUserService {
    public UserDTO createByEmail(UserDTOIn userDTOIn);
    public UserDTO updateByEmail(String email, UserDTOIn userDTOIn);
    public UserDTO findByEmail(String email);
    public UserDTO deleteByEmail(String email);
    List<UserDTO> findAll();
}
