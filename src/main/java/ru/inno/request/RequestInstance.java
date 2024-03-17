package ru.inno.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
@Getter
@Setter
@ToString
@Component
public class RequestInstance {
    private int instanceId;
    @NotNull(message = "Имя обязательного параметра <productType> должно быть заполнено")
    private String productType;
    @NotNull(message = "Имя обязательного параметра <productCode> должно быть заполнено")
    private String productCode;
    @NotNull(message = "Имя обязательного параметра <registerType> должно быть заполнено")
    private String registerType;
    @NotNull(message = "Имя обязательного параметра <mdmCode> должно быть заполнено")
    private String mdmCode;
    @NotNull(message = "Имя обязательного параметра <contractNumber> должно быть заполнено")
    private String contractNumber;
    @NotNull(message = "Имя обязательного параметра <contractDate> должно быть заполнено")
    private Date contractDate;
    @NotNull(message = "Имя обязательного параметра <priority> должно быть заполнено")
    private String priority;
    private float interestRatePenalty;
    private float minimalBalance;
    private float thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private float taxPercentageRate;
    private float technicalOverdraftLimitAmount;
    @NotNull(message = "Имя обязательного параметра <contractId> должно быть заполнено")
    private int contractId;
    @NotNull(message = "Имя обязательного параметра <BranchCode> должно быть заполнено")
    private String branchCode;
    @NotNull(message = "Имя обязательного параметра <IsoCurrencyCode> должно быть заполнено")
    private String isoCurrencyCode;
    @NotNull(message = "Имя обязательного параметра <urgencyCode> должно быть заполнено")
    private String urgencyCode;
    private int referenceCode;
    private AdditionalPropertiesVip additionalPropertiesVip;
    @Valid
    private InstanceArrangement[] instanceArrangement;

    @NoArgsConstructor
    @ToString
    @Getter @Setter
    public static class AdditionalPropertiesVip {
        private Data[] data;
    }

    @NoArgsConstructor
    @Getter @Setter
    public static class Data {
        private String key;
        private String value;
        private String name;
    }

    @NoArgsConstructor
    @ToString
    @Getter @Setter
    public static class InstanceArrangement  {
        // неиспользуемые поля закомментарил
        /*private String generalAgreementId;
        private String supplementaryAgreementId;
        private String arrangementType;
        private int shedulerJobId;*/
        @NotNull(message = "Имя обязательного параметра <number> должно быть заполнено")
        private String number;
        @NotNull(message = "Имя обязательного параметра <openingDate> должно быть заполнено")
        private Date openingDate;
        /*private Date closingDate;
        private Date cancelDate;
        private int validityDuration;
        private String cancellationReason;
        private String status;
        private Date interestCalculationDate;
        private float interestRate;
        private float coefficient;
        private String coefficientAction;
        private float minimumInterestRate;
        private String minimumInterestRateCoefficient;
        private String minimumInterestRateCoefficientAction;
        private double maximalnterestRate;
        private double maximalnterestRateCoefficient;
        private String maximalnterestRateCoefficientAction;*/
    }
}
