package com.tobeto.pair8.services.dtos.color.requests;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteColorRequest {
    @Positive(message = "Lütfen geçerli bir değer giriniz!!!!")
    private int id;
}
