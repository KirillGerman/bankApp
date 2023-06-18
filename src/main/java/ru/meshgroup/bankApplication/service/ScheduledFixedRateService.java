package ru.meshgroup.bankApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.repository.AccountRepository;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class ScheduledFixedRateService {

    private BigDecimal increaseRate;

    private final AccountRepository accountRepository;

    @Scheduled(fixedDelayString = "${scheduled.balanceUpdateInterval}")
    @Transactional
    public void increaseBalance(){
        accountRepository.updateBalance(increaseRate);
    }

    @Value("${scheduled.increaseRate}")
    public void setIncreaseRate(String increaseRate) {
        this.increaseRate = new BigDecimal(increaseRate);
    }
}
