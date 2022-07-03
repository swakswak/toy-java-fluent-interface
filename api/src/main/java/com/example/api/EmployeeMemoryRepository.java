package com.example.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmployeeMemoryRepository {
    private final Map<String, Employee> employees;

    public EmployeeMemoryRepository() {
        this.employees = new ConcurrentHashMap<>();
    }

    public Employee save(Employee employee) {
        employees.put(employee.code(), employee);

        return employees.get(employee.code());
    }

    public List<Employee> findAll() {
        return employees.values().stream().toList();
    }

    public Optional<Employee> findByCode(String code) {
        return Optional.ofNullable(employees.getOrDefault(code, null));
    }

    public void deleteByCode(String code) {
        this.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException(code));

        employees.remove(code);
    }
}
