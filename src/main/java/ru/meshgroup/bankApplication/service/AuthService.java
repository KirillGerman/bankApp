package ru.meshgroup.bankApplication.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.dto.AuthDto;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.security.AuthResponse;
import ru.meshgroup.bankApplication.security.CustomUserDetails;
import ru.meshgroup.bankApplication.security.JwtProvider;

import static ru.meshgroup.bankApplication.utils.ServiceUtils.checkAllParamsNonNull;


@Component
@AllArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final MapStructMapper mapper;

    @Transactional
    public AuthResponse loginUser(AuthDto request){

        checkAllParamsNonNull(request);

        var authentication =  (CustomUserDetails)  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        ObjectUtils.firstNonNull(request.getEmail(), request.getPhone()),
                        request.getPassword())
                ).getPrincipal();

        return new AuthResponse(
                jwtProvider.generateToken(authentication.getUser().getId().toString()),
                mapper.userToUserDto(authentication.getUser())
        );
    }

}
