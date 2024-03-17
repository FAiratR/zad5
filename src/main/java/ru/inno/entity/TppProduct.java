package ru.inno.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "tpp_product")
@Getter
@Setter
@NoArgsConstructor
@Component
public class TppProduct {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_code_id")
    private Integer productCodeId;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "type")
    private String type;
    @Column(name = "number")
    private String number;
    @Column(name = "priority")
    private String priority;
    @Column(name = "date_of_conclusion")
    private Date dateOfConclusion;
    @Column(name = "start_date_time")
    private Date startDateTime;
    @Column(name = "end_date_time")
    private Date endDateTime;
    @Column(name = "days")
    private int days;
    @Column(name = "penalty_rate")
    private BigDecimal penaltyRate;
    @Column(name = "nso")
    private BigDecimal nso;
    @Column(name = "threshold_amount")
    private BigDecimal thresholdAmount;
    @Column(name = "requisite_type")
    private String requisiteType;
    @Column(name = "interest_rate_type")
    private String interestRateType;
    @Column(name = "tax_rate")
    private BigDecimal taxRate;
    @Column(name = "reasone_close")
    private String reasoneClose;
    @Column(name = "state")
    private String state;

    @OneToMany(mappedBy = "productId")
    private List<Agreement> agreements = new ArrayList<>();
    public void addAgreement(Agreement agreement){
        agreement.setProductId(this);
        agreements.add(agreement);
    }
    public Iterator<Agreement> getAgreements() {
        return agreements.iterator();
    }
}
