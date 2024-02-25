package com.tobeto.pair8.controllers;


import com.tobeto.pair8.services.abstracts.AutService;
import com.tobeto.pair8.services.dtos.customer.request.AddRegisterCustomerAndUser;
import com.tobeto.pair8.services.dtos.auth.requests.LoginRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auths")
@AllArgsConstructor
@CrossOrigin
public class AuthsController {

    private final AutService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public Object login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("registerCustomer")
    void registerCustomerAndUserAdd(@RequestBody @Valid AddRegisterCustomerAndUser addRegisterCustomerAndUser){
        authService.registerCustomerAndUserAdd(addRegisterCustomerAndUser);

    }

}
