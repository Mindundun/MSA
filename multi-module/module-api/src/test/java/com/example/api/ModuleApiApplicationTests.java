package com.example.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.common.StringTestFixture;

@SpringBootTest
public class ModuleApiApplicationTests {

    @Test
    void testFixturesTest() {

        String result = StringTestFixture.testGreeting("Mindun");
        System.out.println(result);

        Assertions.assertTrue(result.contains("Mindun"));
    }
}
