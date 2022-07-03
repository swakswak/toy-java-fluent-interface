package com.example.api;

import java.util.List;

public class EmployeeListResource {
    private final List<Employee> contents;
    private final int size;

    public EmployeeListResource(List<Employee> contents) {
        this.contents = contents;
        this.size = contents.size();
    }

    public List<Employee> getContents() {
        return contents;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "EmployeeListResource{" +
                "contents=" + contents +
                ", size=" + size +
                '}';
    }
}
