package com.example.basic_crud_example.services;

import com.example.basic_crud_example.domain.UserDTO;
import com.example.basic_crud_example.domain.inbound.UserDTOIn;

public interface IUserService {
    public UserDTO findByEmail(UserDTOIn userDTOIn);
}
