package com.tobeto.pair8.services.abstracts;

import com.tobeto.pair8.services.dtos.corporateCustomer.requests.*;
import com.tobeto.pair8.services.dtos.corporateCustomer.responses.GetAllCorporateCustomer;
import com.tobeto.pair8.services.dtos.corporateCustomer.responses.GetByIdCorCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {
    void add(AddCorporateCustomerRequest addCorporateCustomerRequest);
    void update(UpdateCorporateCustomerRequest updateCorporateCustomrRequest);
    void delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
     List<GetAllCorporateCustomer> getAll();

    GetByIdCorCustomerResponse getById(int id);


}
