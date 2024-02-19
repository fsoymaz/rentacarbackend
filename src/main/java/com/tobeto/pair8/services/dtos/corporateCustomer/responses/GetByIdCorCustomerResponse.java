package com.tobeto.pair8.services.dtos.corporateCustomer.responses;

import com.tobeto.pair8.services.dtos.user.responses.GetListUserResponse;
import lombok.Data;

@Data
public class GetByIdCorCustomerResponse {
    private String companyName;
    private String taxNo;
    private GetListUserResponse userResponse;
}
