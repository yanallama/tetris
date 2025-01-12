package org.example.tetris;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TetrisMainTest {

    static class TestCase {
        String name;
        String sampleInput;
        int expectedOutput;

        public TestCase(String name, String sampleInput, int expectedOutput) {
            this.name = name;
            this.sampleInput = sampleInput;
            this.expectedOutput = expectedOutput;
        }
    }

    @Test
    void testTetrisSimulation() {
        List<TestCase> testCases = List.of(
                new TestCase("simple test", "Q0", 2),
                new TestCase("simple test 2", "Q0,Q0", 4),
                new TestCase("simple test 2.2", "Q0,Q1", 4),
                new TestCase("completed line test", "I0,I4,Q8", 1),
                new TestCase("simple test 3", "T1,Z3,I4", 4),
                new TestCase("Many blocks test", "Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0", 100)
        );

        for (TestCase testCase : testCases) {
            String[] commands = testCase.sampleInput.split(",");
            int resultHeight = TetrisMain.simulateTetris(commands);

            assertEquals(testCase.expectedOutput, resultHeight,
                    "Test `" + testCase.name + "` failed with input: `" + testCase.sampleInput + "`");
        }
    }
}

