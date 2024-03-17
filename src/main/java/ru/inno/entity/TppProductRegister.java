package ru.inno.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "tpp_product_register")
@Getter
@Setter
@NoArgsConstructor
@Component
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", referencedColumnName = "internal_id")
    private TppRefProductRegisterType type;
    @Column(name = "account")
    private int account;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "state")
    private AccountState state;
    @Column(name = "accountNumber")
    private String accountNumber;

    public TppProductRegister(int productId, TppRefProductRegisterType type, int account, String currencyCode, AccountState state, String accountNumber) {
        this.productId = productId;
        this.type = type;
        this.account = account;
        this.currencyCode = currencyCode;
        this.state = state;
        this.accountNumber = accountNumber;
    }
}
