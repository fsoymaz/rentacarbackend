package com.tobeto.pair8.services.dtos.user.requests;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteUserRequest {
    @Positive(message = "Lütfen geçerli bir id giriniz!!!")
    private int id;
}
