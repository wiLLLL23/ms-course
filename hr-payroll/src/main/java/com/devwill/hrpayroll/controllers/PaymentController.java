package com.devwill.hrpayroll.controllers;

import com.devwill.hrpayroll.domain.Payment;
import com.devwill.hrpayroll.services.PaymentService;
import com.devwill.hrpayroll.services.impl.PaymentServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    private PaymentService service;

    public PaymentController(PaymentServiceImpl service) {
        this.service = service;
    }

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(
            @PathVariable final Long workerId,
            @PathVariable final Integer days
    ) {
        final var payment = service.getPayment(workerId, days);
        return ResponseEntity.ok(payment);
    }
    public ResponseEntity<Payment> getPaymentAlternative(
            final Long workerId,
            final Integer days
    ) {
        final var payment = new Payment("Brann", 400.0, days);
        return ResponseEntity.ok(payment);
    }
}
