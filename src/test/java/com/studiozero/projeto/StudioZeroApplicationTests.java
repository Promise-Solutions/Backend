package com.studiozero.projeto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootTest
class StudioZeroApplicationTests {

    @BeforeAll
    static void loadEnv() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            reader.lines()
                    .filter(line -> line.contains("=") && !line.startsWith("#"))
                    .forEach(line -> {
                        String[] parts = line.split("=", 2);
                        System.setProperty(parts[0], parts[1]);
                    });
        }
    }

    @Test
    void contextLoads() {
    }

}