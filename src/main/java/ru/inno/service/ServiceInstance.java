package ru.inno.service;

import jakarta.persistence.NoResultException;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.inno.entity.*;
import ru.inno.repository.*;
import ru.inno.request.RequestInstance;
import ru.inno.response.ResponseInstance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Validated
public class ServiceInstance implements ServiceInstanceable{
    @Autowired
    ResponseInstance responseInstance;
    @Autowired
    AgreementRepo agreementRepo;
    @Autowired
    TppProductRepo productRepo;
    @Autowired
    TppProductRegisterRepo productRegisterRepo;
    @Autowired
    TppRefProductRegisterTypeRepo registerTypeRepoInst;
    @Autowired
    AccountPoolRepo accountPoolRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    TppRefProductClassRepo productClassRepo;
    @Autowired
    TppRefAccountTypeRepo accountTypeRepo;

    @Transactional
    public ResponseInstance createInstance(@Valid RequestInstance requestInstance) {
        if (requestInstance.getInstanceId() == 0) {
            // Если ИД ЭП в поле Request.Body.instanceId не задано (NULL/Пусто), то выполняется процесс создания нового экземпляра

            // сначала ищем ЭП по номеру договора
            TppProduct existProduct = productRepo.getByNumber(requestInstance.getContractNumber());
            if (existProduct != null) {
                 throw new IllegalArgumentException("Параметр ContractNumber № договора <"+requestInstance.getContractNumber()+"> уже существует для ЭП");
            }

            // Проверяем доп. соглашения на дубли
            for (RequestInstance.InstanceArrangement agreement: requestInstance.getInstanceArrangement()) {
                List<Agreement> agreementsList = agreementRepo.getAllByNumber(agreement.getNumber());
                if (!agreementsList.isEmpty()) {
                    throw new IllegalArgumentException(" Параметр № Дополнительного соглашения (сделки) Number <" + agreement.getNumber() + "> уже существует для ЭП");
                }
            }

            // Также проверим корретный ли пришел ProductCode
            List<TppRefProductRegisterType> registerTypes = registerTypeRepoInst.findAllByProductClassCodeAndAccountType(productClassRepo.getByValue(requestInstance.getProductCode()), accountTypeRepo.getByValue("Клиентский"));
            System.out.println("registerTypes: " + registerTypes);
            if (registerTypes.isEmpty()) {
                throw new NoResultException("КодПродукта <"+requestInstance.getProductCode()+"> не найден в Каталоге продуктов tpp_ref_product_register_type");
            }

            // Добавить строку в таблицу tpp_product, заполнить согласно Request.Body
            TppProduct product = new TppProduct();
            product.setProductCodeId(productClassRepo.getByValue(requestInstance.getProductCode()).getId());
            product.setClientId(requestInstance.getMdmCode());
            product.setType(requestInstance.getProductType());
            product.setNumber(requestInstance.getContractNumber());
            product.setPriority(requestInstance.getPriority());
            product.setDateOfConclusion(requestInstance.getContractDate());
            product.setStartDateTime(new Date());
            product.setPenaltyRate(BigDecimal.valueOf(requestInstance.getInterestRatePenalty()));
            product.setNso(BigDecimal.valueOf(requestInstance.getMinimalBalance()));
            product.setThresholdAmount(BigDecimal.valueOf(requestInstance.getThresholdAmount()));
            product.setInterestRateType(requestInstance.getRateType());
            product.setTaxRate(BigDecimal.valueOf(requestInstance.getTaxPercentageRate()));
            product.setState("OPEN");
            productRepo.save(product);
            responseInstance.getData().setInstanceId(product.getId());

            // Добавить в таблицу ПР (tpp_product_registry) строки
            for (TppRefProductRegisterType registerType: registerTypes) {
                // Получаем номер счёта
                AccountPool accountPool = accountPoolRepo
                        .getByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(requestInstance.getBranchCode(),requestInstance.getIsoCurrencyCode(),requestInstance.getMdmCode(), requestInstance.getPriority(), registerType.getValue());
                List<Account> accounts = accountRepo.getOneByAccountPoolIdAndBussy(accountPool, false);
                Account a = null;
                if (!accounts.isEmpty()) {
                    a = accounts.get(0);
                    a.setBussy(true);
                }
                else
                    throw new NoResultException("В пуле счетов кончились свободные счета");

                TppProductRegister productRegister = new TppProductRegister(product.getId(), registerType, a.getId(), requestInstance.getIsoCurrencyCode(), AccountState.OPEN, a.getAccountNumber());
                productRegisterRepo.save(productRegister);

                // Созданные счета добавляем в ответ
                responseInstance.getData().getRegisterId().add(productRegister.getId());
                System.out.println("добавили productRegister.getId(): " + productRegister.getId());
            }
        }
        else  {
            //Если ИД ЭП в поле Request.Body.instanceId не пустое, то изменяется состав ДС // сделка (доп.Соглашение)

            var product = productRepo.findById(requestInstance.getInstanceId());
            // Проверяем что нашли продукт
            if (product.isEmpty()) {
                throw new IllegalArgumentException("Экземпляр продукта с параметром instanceId <" + requestInstance.getInstanceId() + "> не найден");
            }
            responseInstance.getData().setInstanceId(product.get().getId());

            // Проверяем доп. соглашения на дубли
            for (RequestInstance.InstanceArrangement agreement: requestInstance.getInstanceArrangement()) {
                Iterator<Agreement> agreementsIterator = product.get().getAgreements();
                while (agreementsIterator.hasNext()) {
                    Agreement agreem = agreementsIterator.next();
                    if (agreem.getNumber().equals(agreem.getNumber())) {
                        throw new IllegalArgumentException(" Параметр № Дополнительного соглашения (сделки) Number <"+agreem.getNumber()+"> уже существует для ЭП с ИД "+product.get().getId());
                    }
                }
                // Добавляем новое доп. соглашение
                Agreement agreements = new Agreement(agreement.getNumber());
                product.get().addAgreement(agreements);
                agreementRepo.save(agreements);
                // Созданные доп. соглашения добавляем в ответ
                responseInstance.getData().getSupplementaryAgreementId().add(agreements.getId());
            }
        }

        return responseInstance;
    }
}
