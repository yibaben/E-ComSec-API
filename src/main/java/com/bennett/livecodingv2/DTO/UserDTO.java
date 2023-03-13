package com.bennett.livecodingv2.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    @NotNull(message="please provide a firstname")
    private String username;
    @Email(message="invalid email")
    private String email;
    private String phone_Num;
    private String password;
}
