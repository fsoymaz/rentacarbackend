package com.tobeto.pair8.services.dtos.model.responses;


import com.tobeto.pair8.services.dtos.brand.responses.GetNameBrandResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelNameResponse {
    private String name;
}
