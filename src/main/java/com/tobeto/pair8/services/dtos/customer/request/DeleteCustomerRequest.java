package com.tobeto.pair8.services.dtos.customer.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteCustomerRequest {
    @Positive(message = "Lütfen geçerli bir id giriniz!!!")
    private int id;
}
