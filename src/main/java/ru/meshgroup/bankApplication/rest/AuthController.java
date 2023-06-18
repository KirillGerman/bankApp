package ru.meshgroup.bankApplication.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.AuthDto;
import ru.meshgroup.bankApplication.controllerAdvise.response.AuthResponse;
import ru.meshgroup.bankApplication.service.AuthService;

import javax.validation.Valid;

@Tag(name="2. Auth endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthDto authDto){
        return authService.loginUser(authDto);
    }
}


