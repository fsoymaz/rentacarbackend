package com.tobeto.pair8.services.dtos.brand.requests;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteBrandRequest {
    @Positive(message = "Lütfen geçerli bir id giriniz!!!")
    private int id;
}
