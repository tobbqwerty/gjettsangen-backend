package com.ezdevz.gjett_sangen.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    @NonNull
    private String username;

    @NonNull
    private String password;

}
