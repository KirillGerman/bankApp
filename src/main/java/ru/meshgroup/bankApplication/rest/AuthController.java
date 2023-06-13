package ru.meshgroup.bankApplication.rest;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.AuthDto;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.security.AuthResponse;
import ru.meshgroup.bankApplication.service.AuthService;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthDto authDto){
        return authService.loginUser(authDto);
    }
}


