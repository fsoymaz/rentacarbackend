package com.tobeto.pair8.services.abstracts;

import com.tobeto.pair8.services.dtos.rental.requests.AddRentalRequest;
import com.tobeto.pair8.services.dtos.rental.requests.UpdateRentalRequest;
import com.tobeto.pair8.services.dtos.rental.responses.GetByIdRentalResponse;
import com.tobeto.pair8.services.dtos.rental.responses.GetListRentalResponse;
import com.tobeto.pair8.services.dtos.rental.responses.RentalInfoResponse;

import java.util.List;

public interface RentalService {
    RentalInfoResponse add(AddRentalRequest addRentalRequest);

    void update(UpdateRentalRequest updateRentalRequest);

    void delete(Integer deleteRentalRequest);


    List<GetListRentalResponse> getAll();

    GetByIdRentalResponse getById(int id);


}
