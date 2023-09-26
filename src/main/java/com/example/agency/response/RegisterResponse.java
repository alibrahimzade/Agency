package com.example.agency.response;

import com.example.agency.dao.entity.User;
import com.example.agency.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    String name;
    String email;
    String password;
    Role role;

    public static RegisterResponse buildRegisterDto(User user) {
        return RegisterResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}