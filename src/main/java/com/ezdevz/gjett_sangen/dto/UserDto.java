package com.ezdevz.gjett_sangen.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto {

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
}
