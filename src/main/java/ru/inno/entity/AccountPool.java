package ru.inno.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account_pool")
@Getter
@Setter
@Component
public class AccountPool {
    @Id
    private int id;
    @Column(name = "branch_code")
    private String branchCode;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "mdm_code")
    private String mdmCode;
    @Column(name = "priority_code")
    private String priorityCode;
    @Column(name = "registry_type_code")
    private String registryTypeCode;
    @OneToMany(mappedBy = "accountPoolId")

    @Setter
    @Getter
    private List<Account> accounts = new ArrayList<>();

    @Override
    public String toString() {
        return "AccountPool{" +
                "id=" + id +
                ", accounts=" + accounts +
                '}';
    }
}
