package com.sistem.design.vote.manager.app.adapter;

import com.sistem.design.vote.manager.app.exception.BusinessException;
import com.sistem.design.vote.manager.app.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.sistem.design.vote.manager.app.utils.Constants.UNABLE_TO_VOTE;

@Component
public class CpfValidatorAdapter extends AbstractRestAdapter {
    @Value("${cpf.validator.host}")
    private String host;
    @Value("${cpf.validator.path}")
    private String path;
    @Value("${cpf.validator.port}")
    private int port;
    @Value("${cpf.validator.mock.enabled}")
    private boolean mockEnabled;

    public void validateCpf(String cpf) {
        String response = mockEnabled ? getMock() : buildAdapter()
                .get()
                .uri(getUrl(), cpf)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if (StringUtils.isNotEmpty(response) && response.equals(UNABLE_TO_VOTE)) {
            throw new BusinessException("Error in Vote creation. Invalid user CPF number.");
        }
    }

    private String getUrl() {
        return String.format("%s/%s", host, path);
    }

    private String getMock() {
        return Constants.ABLE_TO_VOTE;
    }
}
