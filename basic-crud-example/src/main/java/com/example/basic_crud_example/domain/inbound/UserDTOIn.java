package com.example.basic_crud_example.domain.inbound;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTOIn {
    @NotBlank
    @NotBlank
    private String name;

    @NotBlank
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{11}$", message = "Phone must be 11 digits")
    private String phoneNumber;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
