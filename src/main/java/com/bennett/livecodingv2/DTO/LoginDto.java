package com.bennett.livecodingv2.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginDto {
    @Email(message="invalid email")
    private String email;
    private String password;

}
