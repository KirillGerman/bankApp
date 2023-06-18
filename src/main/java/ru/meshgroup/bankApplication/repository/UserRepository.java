package ru.meshgroup.bankApplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.meshgroup.bankApplication.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u JOIN u.emails e where e.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM User u JOIN u.phones p where p.phone = :phone")
    Optional<User> findUserByPhone(@Param("phone") String phone);

    Optional<User> findUserByName(String userName);

    Page<User> findAll(Specification<User> spec , Pageable pageable);


}
