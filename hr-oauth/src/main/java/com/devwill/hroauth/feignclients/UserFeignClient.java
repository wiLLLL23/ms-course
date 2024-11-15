package com.devwill.hroauth.feignclients;

import com.devwill.hroauth.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(
        name = "hr-user",
        path = "/users"
)
public interface UserFeignClient {

    @GetMapping(value = "/search")
    ResponseEntity<User> findByEmail(@RequestParam final String email);

    @GetMapping(value = "/{id}")
    ResponseEntity<User> findById(@PathVariable final Long id);
}
