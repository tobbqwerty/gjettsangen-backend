package com.ezdevz.gjett_sangen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDto {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String oldPassword;

    private String newPassword;

}
