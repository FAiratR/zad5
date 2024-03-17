package ru.inno.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.entity.Account;
import ru.inno.entity.AccountPool;

@Repository
public interface AccountPoolRepo extends CrudRepository<AccountPool, Integer> {
    AccountPool getByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(String branchCode
                                                                                      ,String currencyCode
                                                                                      ,String mdmCode
                                                                                      ,String priorityCode
                                                                                      ,String registryTypeCode);
}
