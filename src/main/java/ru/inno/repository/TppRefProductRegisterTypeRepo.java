package ru.inno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.TppRefAccountType;
import ru.inno.entity.TppRefProductClass;
import ru.inno.entity.TppRefProductRegisterType;

import java.util.List;

@Repository
public interface TppRefProductRegisterTypeRepo extends CrudRepository<TppRefProductRegisterType, Integer> {
    TppRefProductRegisterType getByValue(String value);
    boolean existsByValue(String value);
    List<TppRefProductRegisterType> findAllByProductClassCodeAndAccountType(TppRefProductClass productClassCode, TppRefAccountType accountType);
}
