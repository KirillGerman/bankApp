package ru.meshgroup.bankApplication.rest;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.AccountDto;
import ru.meshgroup.bankApplication.dto.TransferRequest;
import ru.meshgroup.bankApplication.service.AccountService;
import ru.meshgroup.bankApplication.utils.AuthUtils;

import javax.validation.Valid;

@Tag(name="3. Account endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/transfer")
    public AccountDto transferMoney(@Valid @RequestBody TransferRequest transferRequest) {
        return accountService.transferMoney(
                AuthUtils.getAuthenticatedUserId(),
                transferRequest.getReceiverId(),
                transferRequest.getAmount()
        );
    }


}
