package org.example.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeTest {
    @Test
    void should_Create_Instance() {
        Employee employee = new Employee("emp-01", "홍길동", "hkd@gmail.com");

        assertNotNull(employee);
    }
}