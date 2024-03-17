package ru.inno.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "tpp_ref_product_class")
@Getter
@Setter
@Component
public class TppRefProductClass {
    @Id
    @Column(name = "internal_id")
    private int id;
    private String value;
    @Column(name = "gbi_code")
    private String gbiCode;
    @Column(name = "gbi_name")
    private String gbiName;
    @Column(name = "product_row_code")
    private String productRowCode;
    @Column(name = "product_row_name")
    private String productRowName;
    @Column(name = "subclass_code")
    private String subClassCode;
    @Column(name = "subclass_name")
    private String subClassName;


}
