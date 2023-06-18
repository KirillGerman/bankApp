package ru.meshgroup.bankApplication.exception;

import lombok.Getter;
import ru.meshgroup.bankApplication.dto.AccountDto;

@Getter
public class AccountNotFoundException extends RuntimeException {

    private AccountDto accountDto;

    public AccountNotFoundException(String message, AccountDto accountDto) {
        super("Account doesn't exists");
        this.accountDto = accountDto;
    }

    public AccountNotFoundException() {
        super("Account doesn't exists");
    }
}
