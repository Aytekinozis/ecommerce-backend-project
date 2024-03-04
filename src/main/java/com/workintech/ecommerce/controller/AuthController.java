package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.RegisterResponse;
import com.workintech.ecommerce.dto.RegistrationUser;
import com.workintech.ecommerce.entity.ApplicationUser;
import com.workintech.ecommerce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/signup")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegistrationUser registrationUser) {
        ApplicationUser createdUser = authenticationService.register(registrationUser.name(), registrationUser.email(), registrationUser.password()
        , registrationUser.role_id(),registrationUser.store());
        return new RegisterResponse("kayıt başarılı bir şekilde gerçekleşti.");
    }
}
