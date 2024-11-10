package com.devwill.hrpayroll.services.impl;

import com.devwill.hrpayroll.domain.Payment;
import com.devwill.hrpayroll.domain.Worker;
import com.devwill.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    private RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment getPayment(final long workerId, final int days) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", String.valueOf(workerId));

        final var worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
