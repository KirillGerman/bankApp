package ru.meshgroup.bankApplication.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.meshgroup.bankApplication.dto.TransferRequest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
class TransferServiceTest {

    @Autowired
    AccountService transferService;


    @Test
    @Transactional
    @Rollback(value = false)
    void test() throws InterruptedException {

        int req = 30;

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(req);

        for (int i = 0; i < req; i++) {
            executorService.submit(() -> {
                try{
                    transferService.transferMoney(9L, 10L, new TransferRequest(new BigDecimal(10))) ;
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();

    }
}