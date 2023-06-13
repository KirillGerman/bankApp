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
public class Account extends BaseEntity  {

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "id")
    private User user;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }
    
}

