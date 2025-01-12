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
                new TestCase("completed line test", "I0,I4,Q8", 1),
                new TestCase("simple test 3", "T1,Z3,I4", 4),
                new TestCase("Many blocks test", "Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0,Q0", 100),

                // Additional cases added based on the imput.txt file
                new TestCase("t1", "Q0,Q1", 4),
                new TestCase("t2", "Q0,Q2,Q4,Q6,Q8", 0),
                new TestCase("t3", "Q0,Q2,Q4,Q6,Q8,Q1", 2),
                new TestCase("t4", "Q0,Q2,Q4,Q6,Q8,Q1,Q1", 4),
                new TestCase("t5", "I0,I4,Q8", 1),
                new TestCase("t6", "I0,I4,Q8,I0,I4", 0),
                new TestCase("t7", "L0,J2,L4,J6,Q8", 2),
                new TestCase("t8", "L0,Z1,Z3,Z5,Z7", 2),
                new TestCase("t9", "T0,T3", 2),
                new TestCase("t10", "T0,T3,I6,I6", 1),
                new TestCase("t11", "I0,I6,S4", 1),
                new TestCase("t12", "T1,Z3,I4", 4),
                new TestCase("t13", "L0,J3,L5,J8,T1", 3),
                new TestCase("t14", "L0,J3,L5,J8,T1,T6", 1),
                new TestCase("t15", "L0,J3,L5,J8,T1,T6,J2,L6,T0,T7", 2),
                new TestCase("t16", "L0,J3,L5,J8,T1,T6,J2,L6,T0,T7,Q4", 1),
                new TestCase("t17", "S0,S2,S4,S6", 8),
                new TestCase("t18", "S0,S2,S4,S5,Q8,Q8,Q8,Q8,T1,Q1,I0,Q4", 8),
                new TestCase("t19", "L0,J3,L5,J8,T1,T6,S2,Z5,T0,T7", 0),
                new TestCase("t20", "Q0,I2,I6,I0,I6,I6,Q2,Q4", 3)
        );

        for (TestCase testCase : testCases) {
            String[] commands = testCase.sampleInput.split(",");
            int resultHeight = TetrisMain.simulateTetris(commands);

            assertEquals(testCase.expectedOutput, resultHeight,
                    "Test `" + testCase.name + "` failed with input: `" + testCase.sampleInput + "`");
        }
    }
}

