package ru.meshgroup.bankApplication.controllerAdvise.response;

import lombok.*;
import ru.meshgroup.bankApplication.dto.AccountDto;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountResponse extends BaseResponse {

    private AccountDto account;

    public AccountResponse(String message) {
        super(message);
    }

    public AccountResponse(String message, AccountDto accountDto) {
        super(message);
        this.account = accountDto;
    }
}
