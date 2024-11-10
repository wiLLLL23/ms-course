package com.devwill.hrpayroll.services;

import com.devwill.hrpayroll.domain.Payment;

public interface PaymentService {
    Payment getPayment(long workerId, int days);
}
