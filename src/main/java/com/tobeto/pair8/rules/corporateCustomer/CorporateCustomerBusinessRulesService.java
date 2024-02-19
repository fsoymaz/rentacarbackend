package com.tobeto.pair8.rules.corporateCustomer;

public interface CorporateCustomerBusinessRulesService {
 void exceptionSameTaxNo(String taxNo);
 void sameCompanyName(String companyName);
 void sameUser(int userId);

}
