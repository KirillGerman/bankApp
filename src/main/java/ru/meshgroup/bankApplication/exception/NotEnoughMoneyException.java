package ru.meshgroup.bankApplication.exception;

import lombok.Getter;
import ru.meshgroup.bankApplication.dto.AccountDto;

@Getter
public class NotEnoughMoneyException extends RuntimeException {

    private AccountDto accountDto;

    public NotEnoughMoneyException(AccountDto accountDto){
        super("Not enough funds");
        this.accountDto =  accountDto;
    }

    public NotEnoughMoneyException(String message, AccountDto accountDto) {
        super(message);
        this.accountDto = accountDto;
    }

    public NotEnoughMoneyException() {
        super("Not enough funds");
    }
}
