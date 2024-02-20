package com.tobeto.pair8.services.dtos.model.requests;

import com.tobeto.pair8.services.dtos.brand.responses.GetAllListBrandResponse;
import com.tobeto.pair8.services.dtos.brand.responses.GetIdBrandResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddModelRequest {
    @NotBlank(message = "model ismi boş olamaz")
    @Size(min = 2, message = "Model ismi en az iki hane olmalıdır.")
    private String name;
    private int brandId;
}
