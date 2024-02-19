package com.tobeto.pair8.services.dtos.city;

import com.tobeto.pair8.services.dtos.district.GetListDistrictResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListCityResponse {
    private String name;
    private List<GetListDistrictResponse> districts;

}