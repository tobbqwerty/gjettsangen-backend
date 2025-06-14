package com.ezdevz.gjett_sangen.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgotPasswordDto {

    @NonNull
    String email;
    @NonNull
    String password;
}
