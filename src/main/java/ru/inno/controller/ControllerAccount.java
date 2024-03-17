package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inno.request.RequestAccount;
import ru.inno.response.ResponseAccount;
import ru.inno.service.ServiceAccount;

@RestController
@Component
public class ControllerAccount {
    @Autowired
    private ServiceAccount serviceAccount;

    @PostMapping("corporate-settlement-account/create/")
    public ResponseAccount createAccount(@RequestBody RequestAccount requestAccount) {
        ResponseAccount responseAccount = serviceAccount.createAccount(requestAccount);
        return responseAccount;
    }

    @Autowired
    public void setServiceAccount(ServiceAccount serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

}
