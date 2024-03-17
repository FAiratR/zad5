package ru.inno.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.TppRefAccountType;

@Repository
public interface TppRefAccountTypeRepo extends CrudRepository<TppRefAccountType, Integer> {
    TppRefAccountType getByValue(String value);
}