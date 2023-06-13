package ru.meshgroup.bankApplication.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.repository.AccountRepository;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class ScheduledFixedRateService {

    private final AccountRepository accountRepository;
    private static final BigDecimal initBalance = BigDecimal.TEN.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);

    @Scheduled(fixedDelayString = "${scheduled.balanceUpdateDelay}")
    @Transactional
    public void increaseBalance(){

        var accounts =  accountRepository.findAll();
        accounts.forEach(
                account -> {
                    var currentBalance = account.getBalance();
                    var increasedBalance = currentBalance.multiply(new BigDecimal(1.1));
                    if (increasedBalance.compareTo(initBalance) < 0) {
                        account.setBalance(increasedBalance);
                    }
                }
        );
        accountRepository.saveAll(accounts);
    }

}
