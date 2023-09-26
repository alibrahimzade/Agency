package com.example.agency.dto;

import com.example.agency.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String name;

    String email;

    String password;
    @Enumerated(EnumType.STRING)
    Role role;
}
