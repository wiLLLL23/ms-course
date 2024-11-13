package com.devwill.hrworker.controllers;

import com.devwill.hrworker.entities.Worker;
import com.devwill.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    private static Logger log = LoggerFactory.getLogger(WorkerController.class);

    @Value("${test.config}")
    private String testConfig;

    private Environment env;
    private WorkerRepository repository;

    public WorkerController(WorkerRepository repository, Environment env) {
        this.repository = repository;
        this.env = env;
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        log.info("CONFIG = {}", testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        final var list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Worker>> findById(
            @PathVariable final Long id)
    {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final var worker = repository.findById(id);
        return ResponseEntity.ok(worker);
    }
}
