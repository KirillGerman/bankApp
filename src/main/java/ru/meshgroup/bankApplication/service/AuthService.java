package ru.meshgroup.bankApplication.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.dto.AuthDto;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.controllerAdvise.response.AuthResponse;
import ru.meshgroup.bankApplication.security.CustomUserDetails;
import ru.meshgroup.bankApplication.security.JwtProvider;

import static ru.meshgroup.bankApplication.utils.ServiceUtils.checkAllNull;


@Component
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final MapStructMapper mapper;

    @Transactional
    public AuthResponse loginUser(AuthDto request){

        checkAllNull("Phone or email not provided", request.getPhone(), request.getEmail());

        var authentication =  (CustomUserDetails)  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        ObjectUtils.firstNonNull(request.getEmail(), request.getPhone()),
                        request.getPassword())
                ).getPrincipal();

        var userId = authentication.getUser().getId().toString();

        return new AuthResponse(jwtProvider.generateToken(userId), userId);
    }

}
