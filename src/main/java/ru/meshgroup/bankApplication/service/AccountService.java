package ru.meshgroup.bankApplication.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.dto.AccountDto;
import ru.meshgroup.bankApplication.dto.TransferRequest;
import ru.meshgroup.bankApplication.exception.AccountNotFoundException;
import ru.meshgroup.bankApplication.exception.NotEnoughMoneyException;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.repository.AccountRepository;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
public class AccountService {

    private final AccountRepository accountRepository;
    private final MapStructMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public AccountDto transferMoney(Long senderId, Long receiverId, TransferRequest transferRequest){

        var accountFrom = accountRepository.findAccountWithLockByUserId(senderId).orElseThrow(AccountNotFoundException::new);
        var accountTo = accountRepository.findByUserId(receiverId).orElseThrow(AccountNotFoundException::new);

        if (accountFrom.getBalance().subtract(transferRequest.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException(mapper.accountToAccountDto(accountFrom));
        }

        accountFrom.setBalance(accountFrom.getBalance().subtract(transferRequest.getAmount()));
        accountTo.setBalance(accountTo.getBalance().add(transferRequest.getAmount()));

        return mapper.accountToAccountDto(accountFrom);

    }

}
