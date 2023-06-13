package ru.meshgroup.bankApplication.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Entity
@Table(name = "phone_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "phone")
public class PhoneData extends BaseEntity {

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    private User user;

    public PhoneData(String phone) {
        this.phone = phone;
    }
}
