package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inno.request.RequestInstance;
import ru.inno.response.ResponseInstance;
import ru.inno.service.ServiceInstance;

@RestController
@ResponseBody
public class ControllerInstance {
    @Autowired
    private ServiceInstance serviceInstance;

    @PostMapping("corporate-settlement-instance/create/")
    public ResponseInstance createInstance(@RequestBody RequestInstance requestInstance) {
        var responseInstance = serviceInstance.createInstance(requestInstance);
        return responseInstance;
    }

    @Autowired
    public void setServiceInstance(ServiceInstance serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

}
