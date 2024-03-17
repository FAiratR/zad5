package ru.inno.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.TppRefProductClass;

@Repository
public interface TppRefProductClassRepo extends CrudRepository<TppRefProductClass, Integer> {
    TppRefProductClass getByValue(String value);
}
