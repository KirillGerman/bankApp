package ru.meshgroup.bankApplication.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.meshgroup.bankApplication.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

public class SpecificationBuilder {


    public static SpecificationBuilder.Builder builder() {
        return new SpecificationBuilder.Builder();
    }

    static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static class Builder {

        private Specification<User> filter = Specification.where(null);

        public SpecificationBuilder.Builder dateOfBirth(String dateOfBirth) {
            if (nonNull(dateOfBirth)) {
                LocalDate date = LocalDate.parse(dateOfBirth, dateTimeFormat);
                filter = filter.and((user, cq, cb) -> cb.greaterThanOrEqualTo(user.get("dateOfBirth"), date));
            }
            return this;
        }

        public SpecificationBuilder.Builder phone(String phone) {
            if (nonNull(phone)) {
                filter = filter.and((user, cq, cb) -> cb.equal(user.join("phones").get("phone"), phone));
            }
            return this;
        }

        public SpecificationBuilder.Builder name(String name) {
            if (nonNull(name)) {
                filter = filter.and((user, cq, cb) -> cb.like(user.get("name"), name + "%"));
            }
            return this;
        }

        public SpecificationBuilder.Builder email(String email) {
            if (nonNull(email)) {
                filter = filter.and((user, cq, cb) -> cb.equal(user.join("emails").get("email"), email));
            }
            return this;
        }

        public Specification<User> build() {
            return filter;
        }

    }

}