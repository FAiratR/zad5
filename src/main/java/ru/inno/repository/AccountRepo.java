package ru.inno.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.Account;
import ru.inno.entity.AccountPool;

import java.util.List;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
   List<Account> getOneByAccountPoolIdAndBussy(AccountPool accountPool, boolean bussy);
}
