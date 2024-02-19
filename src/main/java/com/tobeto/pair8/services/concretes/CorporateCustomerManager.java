package com.tobeto.pair8.services.concretes;

import com.tobeto.pair8.core.utilities.mappers.services.ModelMapperService;
import com.tobeto.pair8.entities.concretes.CorporateCustomer;
import com.tobeto.pair8.entities.concretes.Customer;
import com.tobeto.pair8.entities.concretes.User;
import com.tobeto.pair8.repositories.CorporateCustomerRepository;
import com.tobeto.pair8.repositories.UserRepository;
import com.tobeto.pair8.rules.corporateCustomer.CorporateCustomerBusinessRulesService;
import com.tobeto.pair8.rules.user.UserBusinessRulesService;
import com.tobeto.pair8.services.abstracts.CorporateCustomerService;
import com.tobeto.pair8.services.dtos.corporateCustomer.requests.*;
import com.tobeto.pair8.services.dtos.corporateCustomer.responses.GetAllCorporateCustomer;
import com.tobeto.pair8.services.dtos.corporateCustomer.responses.GetByIdCorCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;
    private final CorporateCustomerBusinessRulesService corporateCustomerBusinessRulesService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserBusinessRulesService userBusinessRulesService;
    @Override
    public void add(AddCorporateCustomerRequest addCorporateCustomerRequest) {
        corporateCustomerBusinessRulesService.exceptionSameTaxNo(addCorporateCustomerRequest.getTaxNo());
        corporateCustomerBusinessRulesService.sameCompanyName(addCorporateCustomerRequest.getCompanyName());
        corporateCustomerBusinessRulesService.sameUser(addCorporateCustomerRequest.getUserId());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(addCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomer.setId(null);
        corporateCustomerRepository.save(corporateCustomer);
    }

    @Override
    public void update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        corporateCustomerBusinessRulesService.exceptionSameTaxNo(updateCorporateCustomerRequest.getTaxNo());
        corporateCustomerBusinessRulesService.sameCompanyName(updateCorporateCustomerRequest.getCompanyName());
        corporateCustomerBusinessRulesService.sameUser(updateCorporateCustomerRequest.getUserId());


        CorporateCustomer corporateCustomerToUpdate = corporateCustomerRepository.findById(updateCorporateCustomerRequest.getId()).orElseThrow();
        this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, corporateCustomerToUpdate);
        corporateCustomerRepository.saveAndFlush(corporateCustomerToUpdate);
    }


    @Override
    public void delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        CorporateCustomer corporateCustomerDelete = corporateCustomerRepository.findById(deleteCorporateCustomerRequest.getId()).orElseThrow();
        corporateCustomerRepository.delete(corporateCustomerDelete);
    }

    @Override
    public List<GetAllCorporateCustomer> getAll() {
        List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
        List<GetAllCorporateCustomer> getAllCorporateCustomers = corporateCustomers.stream()
                .map(corporateCustomer -> this.modelMapperService
                        .forResponse().map(corporateCustomer, GetAllCorporateCustomer.class))
                .collect(Collectors.toList());
        return getAllCorporateCustomers;

    }

    @Override
    public GetByIdCorCustomerResponse getById(int id) {
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(id).orElseThrow();
        GetByIdCorCustomerResponse getByIdCorCustomerResponse = this.modelMapperService.forResponse().map(corporateCustomer, GetByIdCorCustomerResponse.class);
        return getByIdCorCustomerResponse;
    }


}
