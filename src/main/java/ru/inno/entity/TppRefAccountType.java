package ru.inno.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "tpp_ref_account_type")
@Getter
@Setter
@Component
public class TppRefAccountType {
    @Id
    @Column(name = "internal_id")
    private int id;
    private String value;

    @Override
    public String toString() {
        return "TppRefAccountType{" +
                "internal_id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}