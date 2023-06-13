package ru.meshgroup.bankApplication.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.meshgroup.bankApplication.dto.AuthDto;
import ru.meshgroup.bankApplication.model.User;

import static java.util.Objects.nonNull;

public class ServiceUtils {

    public static Specification<User> getUserSpecification(String dateOfBirth, String phone, String name, String email) {

        Specification<User> filter = Specification.where(null);

        if (nonNull(dateOfBirth)) {
            filter = filter.and((user, cq, cb) -> cb.greaterThanOrEqualTo(user.get("dateOfBirth"), dateOfBirth));
        } else if (nonNull(phone)) {
            filter = filter.and((user, cq, cb) -> cb.equal(user.join("phones").get("phone"), phone));
        } else if (nonNull(name)) {
            filter = filter.and((user, cq, cb) -> cb.like(user.get("name"), name + "%"));
        } else if (nonNull(email)) {
            filter = filter.and((user, cq, cb) -> cb.equal(user.join("emails").get("email"), email));
        }
        return filter;
    }

    public static Pageable getPageSize(Integer page, Integer size) {

        Pageable pageable = Pageable.unpaged();

        if (nonNull(page) && nonNull(size)) {
            pageable = PageRequest.of(page, size);
        }

        return pageable;
    }

    public static void checkAllParamsNonNull(Object ... values) {
        if (ObjectUtils.allNull(values)) {
            throw new IllegalArgumentException();
        }
    }

}
