package ru.inno.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Entity
@Table(name = "tpp_ref_product_register_type")
@Getter
@Setter
@Component
public class TppRefProductRegisterType {
    @Id
    @Column(name = "internal_id")
    private int id;
    @Column(name = "value")
    private String value;
    @Column(name = "register_type_name")
    private String registerTypeName;
    @ManyToOne
    @JoinColumn(name = "product_class_code", referencedColumnName = "internal_id")
    private TppRefProductClass productClassCode;
    @Column(name = "register_type_start_date")
    private Date registerTypeStartDate;
    @Column(name = "register_type_end_date")
    private Date registerTypeEndDate;
    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "internal_id")
    private TppRefAccountType accountType;

}
