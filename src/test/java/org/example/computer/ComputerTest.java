package org.example.computer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ComputerTest {

    @Test
    void should_Computer_Not_Null() {
        Computer computer = new Computer();

        assertNotNull(computer);
    }
}