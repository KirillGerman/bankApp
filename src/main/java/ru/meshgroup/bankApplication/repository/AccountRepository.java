package ru.meshgroup.bankApplication.repository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.meshgroup.bankApplication.model.Account;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.Optional;


public interface AccountRepository extends CrudRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findAccountWithLockById(Long userId);

    Optional<Account> findByUserId(Long id);

    @Modifying
    @Query("update Account a set a.balance = a.balance * :rate WHERE a.balance * :rate < a.maxBalance")
    void updateBalance(@Param("rate") BigDecimal increaseRate);

}
