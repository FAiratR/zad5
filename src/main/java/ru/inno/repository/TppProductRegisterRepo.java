package ru.inno.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.TppProduct;
import ru.inno.entity.TppProductRegister;
import ru.inno.entity.TppRefProductRegisterType;

@Repository
public interface TppProductRegisterRepo extends CrudRepository<TppProductRegister, Integer> {
    boolean existsByProductIdAndType(int productId, TppRefProductRegisterType type);
    boolean deleteByProductId(int productId);
    boolean existsByProductId(int productId);
    TppProductRegister getByProductId(int productId);
}
