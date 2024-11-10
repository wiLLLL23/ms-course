package com.devwill.hrpayroll.feignclients;

import com.devwill.hrpayroll.domain.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Component
@FeignClient(
        name = "hr-worker",
        url = "localhost:8001",
        path = "/workers"
)
public interface WorkerFeignClient {

    @GetMapping(value = "/{id}")
    ResponseEntity<Optional<Worker>> findById(@PathVariable final Long id);
}
