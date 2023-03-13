package com.bennett.livecodingv2.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class RegisterResponse {

    private String message;
    private LocalDateTime timeStamp;
}
