package ru.meshgroup.bankApplication.model;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user")
public class User extends BaseEntity{


    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "email")
    private List<EmailData> emails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "phone")
    private List<PhoneData> phones;

    public User(String name, LocalDate dateOfBirth, String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public User(Long userId) {
    }
}
