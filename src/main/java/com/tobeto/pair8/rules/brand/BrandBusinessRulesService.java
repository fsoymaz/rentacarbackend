package com.tobeto.pair8.rules.brand;

import com.tobeto.pair8.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair8.services.dtos.brand.requests.DeleteBrandRequest;
import com.tobeto.pair8.services.dtos.brand.requests.UpdateBrandRequest;

public interface BrandBusinessRulesService {
    void exceptionSameBrand(String name);
}
