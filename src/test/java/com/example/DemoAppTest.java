package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DemoAppTest {

    @Test
    public void testProcessValueUnderLimit() {
        DemoApp app = new DemoApp();
        assertFalse(app.processValue(3)); // 3 * 2 = 6 (Under limit of 10)
    }

    @Test
    public void testProcessValueOverLimit() {
        DemoApp app = new DemoApp();
        assertTrue(app.processValue(6)); // 6 * 2 = 12 (Over limit of 10)
    }
}