package ru.inno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.TppProduct;
import ru.inno.entity.TppRefProductRegisterType;

@Repository
public interface TppProductRepo extends CrudRepository<TppProduct, Integer> {
    TppProduct getByNumber(String value);
    boolean existsByNumber(String value);
    TppProduct getById(Integer id);
    boolean existsById(Integer id);
}
