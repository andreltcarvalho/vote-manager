package com.sistem.design.vote.manager.app.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CpfValidatorAdapter extends AbstractRestAdapter {
    @Value("${cpf.validator.host}")
    private String host;

    @Value("${cpf.validator.port}")
    private int databasePort;

    public String validateCpf(String id) {
        return buildAdapter()
                .get()
                .uri(String.format("%s:%s/user/%s", host, databasePort, id))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
