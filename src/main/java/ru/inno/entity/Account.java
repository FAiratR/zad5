package ru.inno.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "account")
//@ToString
@Getter
@Setter
@Component
public class Account {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "account_pool_id", referencedColumnName = "id")
    private AccountPool accountPoolId;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "bussy")
    private boolean bussy;
}
