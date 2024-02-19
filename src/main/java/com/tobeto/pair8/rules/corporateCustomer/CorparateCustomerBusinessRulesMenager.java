package com.tobeto.pair8.rules.corporateCustomer;

import com.tobeto.pair8.core.utilities.exceptions.entityException.DuplicateCompanyNameException;
import com.tobeto.pair8.core.utilities.exceptions.entityException.DuplicateTaxNoException;
import com.tobeto.pair8.core.utilities.exceptions.entityException.UserAlreadyExistsException;
import com.tobeto.pair8.repositories.CorporateCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tobeto.pair8.core.utilities.constants.CorporateConstants.*;

@Service
@AllArgsConstructor

public class CorparateCustomerBusinessRulesMenager implements CorporateCustomerBusinessRulesService {
    private final CorporateCustomerRepository corporateCustomerRepository;

    @Override
    public void exceptionSameTaxNo(String taxNo) {
        if (corporateCustomerRepository.existsByTaxNo(taxNo)) {
            throw new DuplicateTaxNoException(DUPLICATE_TAX_NO + taxNo);
        }
    }

    @Override
    public void sameCompanyName(String companyName) {
        if (corporateCustomerRepository.existsByCompanyName(companyName)) {
            throw new DuplicateCompanyNameException(DUPLICATE_COMPANY_NAME + companyName);
        }
    }

    @Override
    public void sameUser(int userId) {
        if (corporateCustomerRepository.existsByUserId(userId)) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS  + userId);
        }
    }
}
