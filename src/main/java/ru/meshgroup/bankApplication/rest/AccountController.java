package ru.meshgroup.bankApplication.rest;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.AccountDto;
import ru.meshgroup.bankApplication.dto.TransferRequest;

import ru.meshgroup.bankApplication.security.CustomUserDetails;
import ru.meshgroup.bankApplication.service.AccountService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    @PostMapping("/transfer/{receiverId}")
    public AccountDto transferMoney(Authentication authentication, @PathVariable Long receiverId, @Valid @RequestBody TransferRequest transferRequest) {
        var senderId = ((CustomUserDetails) authentication.getPrincipal()).getUser().getId();
        return accountService.transferMoney(senderId, receiverId, transferRequest);
    }


}
