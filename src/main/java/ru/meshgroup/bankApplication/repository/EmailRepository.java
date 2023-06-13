package ru.meshgroup.bankApplication.repository;

import liquibase.pro.packaged.L;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.meshgroup.bankApplication.model.EmailData;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface EmailRepository extends CrudRepository<EmailData, Long> {

    Optional<EmailData> findByEmail(String email);

    Optional<EmailData> findByEmailAndUserId(Long userId, String email);

    List<EmailData> findAllByUserId(Long userId);

    @Modifying
    @Query(value = "INSERT INTO email_data (id, user_id, email) VALUES (nextval('hibernate_sequence'), ?1, ?2)" , nativeQuery = true)
    void insertEmailData(Long userId, String emailData);

}
