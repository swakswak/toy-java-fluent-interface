package org.example.http;

import java.util.List;

public record EmployeeList(List<Employee> contents, int size) {
}
