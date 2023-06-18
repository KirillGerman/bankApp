package ru.meshgroup.bankApplication.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.dto.AccountDto;
import ru.meshgroup.bankApplication.exception.AccountNotFoundException;
import ru.meshgroup.bankApplication.exception.NotEnoughMoneyException;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.repository.AccountRepository;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final MapStructMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public AccountDto transferMoney(Long senderId, Long receiverId, BigDecimal amount){

        var accountFrom = accountRepository.findAccountWithLockById(senderId).orElseThrow(AccountNotFoundException::new);
        var accountTo = accountRepository.findById(receiverId).orElseThrow(AccountNotFoundException::new);

        if (accountFrom.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException(mapper.accountToAccountDto(accountFrom));
        }

        accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
        accountTo.setBalance(accountTo.getBalance().add(amount));

        return mapper.accountToAccountDto(accountFrom);

    }

}
