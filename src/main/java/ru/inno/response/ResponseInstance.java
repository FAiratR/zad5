package ru.inno.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Component
public class ResponseInstance {
    private InsData data = new InsData();

    @Getter
    @Setter
    @ToString
    public class InsData {
        private Integer instanceId;
        List<Integer> registerId = new ArrayList<>();
        List<Integer> supplementaryAgreementId = new ArrayList<>();
    }
}
