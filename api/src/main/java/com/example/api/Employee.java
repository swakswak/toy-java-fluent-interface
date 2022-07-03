package com.example.api;

public record Employee(String code, String name, String email) {
    @Override
    public String toString() {
        return "Employee{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
