package com.devwill.hrpayroll.services.impl;

import com.devwill.hrpayroll.domain.Payment;
import com.devwill.hrpayroll.feignclients.WorkerFeignClient;
import com.devwill.hrpayroll.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private WorkerFeignClient client;

    public PaymentServiceImpl(WorkerFeignClient client) {
        this.client = client;
    }

    @Override
    public Payment getPayment(final long workerId, final int days) {
        final var optionalWorker = client.findById(workerId).getBody();
        if (optionalWorker.isEmpty()) {
            return null;
        }

        final var worker = optionalWorker.get();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
