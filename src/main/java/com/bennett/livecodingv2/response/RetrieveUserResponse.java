package com.bennett.livecodingv2.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class RetrieveUserResponse {
    private String message;
    private LocalDateTime timeStamp;
}
