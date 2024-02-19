package com.tobeto.pair8.services.dtos.invoice.requests;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteInvoiceRequest {
    @Positive(message = "Lütfen geçerli bir id giriniz!!!")
    private int id;
}
