package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathematicalCalculatorSubractOneTest {

    private int arraySizeTest = 200;
    private MathematicalCalculator testSubject = new MathematicalCalculator(arraySizeTest);

    @Test
    void shouldThrowAnArithmeticExceptionWhenSubtractOneToZeroArray() {
        assertThrows(ArithmeticException.class, () -> {
            int[] value = new int[arraySizeTest];
            value[0] = 0;

            testSubject.subtractOne(value);
        });
    }

    @Test
    void shouldSubtractOneToOneArray() {

        int[] value = new int[arraySizeTest];
        value[0] = 1;

        int[] actualResult = testSubject.subtractOne(value);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 0;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldSubtractOneTo3DigitNumberWithNoCarriedOver() {

        int[] value = new int[arraySizeTest];
        value[0] = 9;
        value[1] = 9;
        value[2] = 9;

        int[] actualResult = testSubject.subtractOne(value);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 8;
        expectedResult[1] = 9;
        expectedResult[2] = 9;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldSubtractOneToANumberWithACarriedOver() {

        int[] value = new int[arraySizeTest];
        value[0] = 0;
        value[1] = 1;
        value[2] = 1;

        int[] actualResult = testSubject.subtractOne(value);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 9;
        expectedResult[1] = 0;
        expectedResult[2] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldSubtractOneToANumberWithMultipleCarriedOvers() {

        int[] value01 = new int[arraySizeTest];
        value01[0] = 0;
        value01[1] = 0;
        value01[2] = 1;

        int[] actualResult = testSubject.subtractOne(value01);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 9;
        expectedResult[1] = 9;
        expectedResult[2] = 0;

        assertThat(actualResult, equalTo(expectedResult));
    }


}
