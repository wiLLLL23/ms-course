package com.devwill.hrpayroll.services.impl;

import com.devwill.hrpayroll.domain.Payment;
import com.devwill.hrpayroll.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Payment getPayment(long workerId, int days) {
        return new Payment("Will", 1700.00, days);
    }
}
