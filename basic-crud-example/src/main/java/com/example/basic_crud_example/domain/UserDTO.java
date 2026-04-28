package com.example.basic_crud_example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    String id;
    String name;
    String email;
    String phoneNumber;
    String password;
}
