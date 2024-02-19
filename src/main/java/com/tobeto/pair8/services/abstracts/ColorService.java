package com.tobeto.pair8.services.abstracts;

import com.tobeto.pair8.services.dtos.color.requests.AddColorRequest;
import com.tobeto.pair8.services.dtos.color.requests.DeleteColorRequest;
import com.tobeto.pair8.services.dtos.color.requests.UpdateColorRequest;
import com.tobeto.pair8.services.dtos.color.responses.GetAllListColorResponse;

import java.util.List;

public interface ColorService {
    void add(AddColorRequest addColorRequest);
    void update(UpdateColorRequest updateColorRequest);
    void delete(DeleteColorRequest deleteColorRequest);
    List<GetAllListColorResponse> getAll();


}
