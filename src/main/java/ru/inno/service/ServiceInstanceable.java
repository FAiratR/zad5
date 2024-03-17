package ru.inno.service;

import jakarta.validation.Valid;
import ru.inno.request.RequestInstance;
import ru.inno.response.ResponseInstance;

public interface ServiceInstanceable {
    public ResponseInstance createInstance(@Valid RequestInstance requestInstance);
}
