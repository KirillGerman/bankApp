package ru.meshgroup.bankApplication.repository;

import org.springframework.data.repository.CrudRepository;
import ru.meshgroup.bankApplication.model.PhoneData;


public interface PhoneRepository extends CrudRepository<PhoneData, Long> {
}
