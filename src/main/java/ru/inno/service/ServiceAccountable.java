package ru.inno.service;

import jakarta.validation.Valid;
import ru.inno.request.RequestAccount;
import ru.inno.response.ResponseAccount;

public interface ServiceAccountable {
    public ResponseAccount createAccount(@Valid RequestAccount requestAccount);
}
