package ru.inno.service;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.inno.entity.*;
import ru.inno.repository.*;
import ru.inno.request.RequestAccount;
import ru.inno.response.ResponseAccount;
import java.util.List;

@Validated
@Service
public class ServiceAccount implements ServiceAccountable{
    private ResponseAccount responseAccount;
    private TppProductRepo productRepo;
    private TppProductRegisterRepo productRegisterRepo;
    private TppRefProductRegisterTypeRepo registerTypeRepo;
    private AccountPoolRepo accountPoolRepo;
    private AccountRepo accountRepo;

    @Transactional
    public ResponseAccount createAccount(@Valid RequestAccount requestAccount) {
         if (registerTypeRepo.existsByValue(requestAccount.getRegistryTypeCode())) {
             TppRefProductRegisterType registerType = registerTypeRepo.getByValue(requestAccount.getRegistryTypeCode());
            if (productRegisterRepo.existsByProductIdAndType(requestAccount.getInstanceId(), registerType)) {
                throw new IllegalArgumentException("Параметр registryTypeCode тип регистра <"+registerType.getValue() + "> уже существует для ЭП c Id = " + requestAccount.getInstanceId());
            } else {
                //Ищем счет в пуле
                AccountPool accountPool = accountPoolRepo
                        .getByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(requestAccount.getBranchCode(),requestAccount.getCurrencyCode(),requestAccount.getMdmCode(),requestAccount.getPriorityCode(), requestAccount.getRegistryTypeCode());
                List<Account> accounts = accountRepo.getOneByAccountPoolIdAndBussy(accountPool, false);
                if (!accounts.isEmpty()) {
                    Account a = accounts.get(0);
                    a.setBussy(true);
                    accountPoolRepo.save(accountPool);
                    // создаем продуктовый регистр
                    TppProductRegister productRegister = new TppProductRegister(requestAccount.getInstanceId(), registerType, a.getId(), requestAccount.getCurrencyCode(), AccountState.RESERVED, a.getAccountNumber());
                    productRegisterRepo.save(productRegister);
                    responseAccount.getData().setAccountId(productRegister.getId());
                }
                else {
                    throw new IllegalArgumentException("В пуле счетов не найен свободный счет");
                }
            }
        }
        else  {
             throw new IllegalArgumentException("Код Продукта <" + requestAccount.getRegistryTypeCode() + "> не найден в Каталоге продуктов <tpp_ref_product_register_type> для данного типа Регистра");
        }
        return responseAccount;
    }
    @Autowired
    public void setResponseAccount(ResponseAccount responseAccount) {
        this.responseAccount = responseAccount;
    }
    @Autowired
    public void setProductRepo(TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Autowired
    public void setProductRegisterRepo(TppProductRegisterRepo productRegisterRepo) {
        this.productRegisterRepo = productRegisterRepo;
    }
    @Autowired
    public void setRegisterTypeRepo(TppRefProductRegisterTypeRepo registerTypeRepo) {
        this.registerTypeRepo = registerTypeRepo;
    }
    @Autowired
    public void setAccountPoolRepo(AccountPoolRepo accountPoolRepo) {
        this.accountPoolRepo = accountPoolRepo;
    }
    @Autowired
    public void setAccountRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
}
