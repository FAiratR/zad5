package ru.inno.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ToString
public class ResponseAccount {
    private AccData data = new AccData();
    @ToString
    @Getter
    @Setter
    public class AccData {
        private int accountId;
    }
}
