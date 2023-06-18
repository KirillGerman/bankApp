package ru.meshgroup.bankApplication.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(name="account_generator", sequenceName = "account", allocationSize = 1)
    @Column(name = "id")
    private Long id;


    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "max_balance")
    private BigDecimal maxBalance;

    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "id")
    private User user;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    @PrePersist
    void addMaxBalance(){
        maxBalance = balance.multiply(new BigDecimal(2));
    }
}

