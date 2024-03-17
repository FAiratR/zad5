package ru.inno.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "agreement")
@Getter
@Setter
@NoArgsConstructor
@Component
public class Agreement {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private TppProduct productId;
    @Column(name = "general_agreement_id")
    private String generalAgreementId;
    @Column(name = "supplementary_agreement_id")
    private String supplementaryAgreementId;
    @Column(name = "arrangement_type")
    private String arrangementType;
    @Column(name = "sheduler_job_id")
    private int shedulerJobId;
    private String number;
    @Column(name = "opening_date")
    private Date openingDate;
    @Column(name = "closing_date")
    private Date closingDate;
    @Column(name = "cancel_date")
    private Date cancelDate;
    @Column(name = "validity_duration")
    private int validityDuration;
    @Column(name = "cancellation_reason")
    private String cancellationReason;
    private String status;
    @Column(name = "interest_calculation_date")
    private Date interestCalculationDate;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    private BigDecimal coefficient;
    @Column(name = "coefficient_action")
    private String coefficientAction;
    @Column(name = "minimum_interest_rate")
    private BigDecimal minimumInterestRate;
    @Column(name = "minimum_interest_rate_coefficient")
    private BigDecimal minimumInterestRateCoefficient;
    @Column(name = "minimum_interest_rate_coefficient_action")
    private String minimumInterestRateCoefficientAction;
    public Agreement(String number) {
        this.number = number;
    }
}
