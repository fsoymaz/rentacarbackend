package com.tobeto.pair8.services.dtos.corporateCustomer.responses;

import com.tobeto.pair8.services.dtos.user.responses.GetListUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCorporateCustomer {
    private int id;
    private String companyName;
    private String taxNo;
    private GetListUserResponse user;
}
