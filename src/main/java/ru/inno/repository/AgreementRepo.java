package ru.inno.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.Agreement;

import java.util.List;

@Repository
public interface AgreementRepo extends CrudRepository<Agreement, Integer> {
    boolean existsByNumber(String value);
    List<Agreement> getAllByNumber(String value);
}
