package com.tobeto.pair8.controllers;

import com.tobeto.pair8.services.abstracts.CorporateCustomerService;
import com.tobeto.pair8.services.dtos.corporateCustomer.requests.*;
import com.tobeto.pair8.services.dtos.corporateCustomer.responses.GetAllCorporateCustomer;
import com.tobeto.pair8.services.dtos.corporateCustomer.responses.GetByIdCorCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/corporateCustomers")
@AllArgsConstructor
public class CorporateCustomerController {
    private final CorporateCustomerService corporateCustomerService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCorporateCustomerRequest addCorporateCustomerRequest) {
        corporateCustomerService.add(addCorporateCustomerRequest);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<GetAllCorporateCustomer> getAll() {
        return corporateCustomerService.getAll();
    }



    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        corporateCustomerService.update(updateCorporateCustomerRequest);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        corporateCustomerService.delete(deleteCorporateCustomerRequest);
    }

    @GetMapping("/getById")
    public GetByIdCorCustomerResponse getById(@RequestParam @Valid int id) {
        return corporateCustomerService.getById(id);
    }


}
