package com.tobeto.pair8.services.dtos.model.requests;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteModelRequest {
   @Positive(message = "Lütfen geçerli bir id giriniz!!!")
   private int id;
}
