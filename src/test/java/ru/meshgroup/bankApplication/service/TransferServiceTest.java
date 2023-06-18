package ru.meshgroup.bankApplication.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.ActiveProfiles;
import ru.meshgroup.bankApplication.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
//@ExtendWith(PostgreSQLExtension.class)
@ActiveProfiles("dev")
class TransferServiceTest {

    @Autowired
    AccountService transferService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void shouldHaveCorrectBalanceWhenTransfer() throws InterruptedException {

        int reqNumber = 30;
        var amount = BigDecimal.TEN;
        var transferredAmount = new BigDecimal(reqNumber).multiply(amount);

        var accountSenderBefore = accountRepository.findById(1L).get();
        var accountReceiverBefore = accountRepository.findById(2L).get();

        transferMoney(reqNumber, amount, 1L, 2L);

        var accountSenderAfter = accountRepository.findById(1L).get();
        var accountReceiverAfter = accountRepository.findById(2L).get();


        Assertions.assertTrue(accountSenderBefore.getBalance().subtract(accountSenderAfter.getBalance()).compareTo(transferredAmount) == 0);
        Assertions.assertTrue(accountReceiverAfter.getBalance().subtract(accountReceiverBefore.getBalance()).compareTo(transferredAmount) == 0);

    }


    private void transferMoney(int req, BigDecimal amount, Long senderId, Long receiverI) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(req);
        for (int i = 0; i < req; i++) {
            executorService.submit(() -> {
                try{
                    transferService.transferMoney(senderId, receiverI, amount) ;
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}