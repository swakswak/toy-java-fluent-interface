package com.example.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final Logger log;
    private final EmployeeMemoryRepository repository;

    public EmployeeController(EmployeeMemoryRepository repository) {
        this.log = LoggerFactory.getLogger(this.getClass());
        this.repository = repository;
    }

    @PostMapping
    public Employee post(@RequestBody Employee employee) {
        log.info("[post] resource={}", employee);

        return repository.save(employee);
    }

    @GetMapping
    public EmployeeListResource get(@RequestHeader("x-user") String xUser) {
        log.info("[get] xUser={}", xUser);

        return new EmployeeListResource(repository.findAll());
    }

    @GetMapping("/{code}")
    public Employee get(@RequestHeader("x-user") String xUser, @PathVariable("code") String code) {
        log.info("[get] xUser={}, code={}", xUser, code);

        return repository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException(code));
    }

    @PutMapping("/{code}")
    public Employee put(@PathVariable("code") String code, @RequestBody Employee employee) {
        log.info("[put] code={}, employee={}", code, employee);

        repository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException(code));

        return repository.save(employee);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("code") String code, @RequestBody Employee employee) {
        log.info("[delete] code={}, employee={}", code, employee);

        repository.deleteByCode(code);
    }
}
