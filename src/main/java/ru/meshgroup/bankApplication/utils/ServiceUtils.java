package ru.meshgroup.bankApplication.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.meshgroup.bankApplication.model.User;

import static java.util.Objects.nonNull;

public class ServiceUtils {

    public static Specification<User> getUserSpecification(String dateOfBirth, String phone, String name, String email) {
        return SpecificationBuilder.builder()
                .dateOfBirth(dateOfBirth)
                .phone(phone)
                .name(name)
                .email(email)
                .build();
    }

    public static Pageable getPageSize(Integer page, Integer size) {

        Pageable pageable = Pageable.unpaged();

        if (nonNull(page) && nonNull(size)) {
            pageable = PageRequest.of(page, size);
        }

        return pageable;
    }

    public static void checkAllNull(String message, Object ... values) {
        if (ObjectUtils.allNull(values)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkAnyNull(String message, Object ... values) {
        if (ObjectUtils.anyNull(values)) {
            throw new IllegalArgumentException(message);
        }
    }

}
