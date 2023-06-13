package ru.meshgroup.bankApplication.repository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import ru.meshgroup.bankApplication.model.Account;
import ru.meshgroup.bankApplication.model.User;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;


public interface AccountRepository extends CrudRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findAccountWithLockByUserId(Long userId);

    Optional<Account> findByUserId(Long id);

}
