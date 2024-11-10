package com.devwill.hrworker.controllers;

import com.devwill.hrworker.entities.Worker;
import com.devwill.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {
    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

    private Environment env;
    private WorkerRepository repository;

    public WorkerController(WorkerRepository repository, Environment env) {
        this.repository = repository;
        this.env = env;
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
        logger.info("PORT = {}", env.getProperty("local.server.port"));

        final var worker = repository.findById(id);
        return ResponseEntity.ok(worker);
    }
}
