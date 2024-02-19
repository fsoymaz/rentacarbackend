package com.tobeto.pair8.services.dtos.rental.requests;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteRentalRequest {
    @Positive(message = "Lütfen geçerli bir id giriniz!!!")
    private int id;
}
