package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MathematicalCalculatorSumTest {

    private int arraySizeTest = 200;
    private MathematicalCalculator testSubject = new MathematicalCalculator(arraySizeTest);

    @Test
    void shouldAddAddendsWithNoAmountCarriedOver() {

        int arraySizeTest = 200;
        int[] addend01 = new int[arraySizeTest];
        addend01[0] = 2;
        addend01[1] = 1;

        int[] actualResult = testSubject.sum(addend01, addend01);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 4;
        expectedResult[1] = 2;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldAddAddendsWithAmountCarriedOver() {

        int[] addend01 = new int[arraySizeTest];
        addend01[0] = 2;
        addend01[1] = 1;
        int[] addend02 = new int[arraySizeTest];
        addend02[0] = 9;
        addend02[1] = 1;

        int[] actualResult = testSubject.sum(addend01, addend02);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 1;
        expectedResult[1] = 3;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldAddAddendsWithASequenceOfAmountCarriedOver() {

        int[] addend01 = new int[arraySizeTest];
        addend01[0] = 2;
        addend01[1] = 9;
        addend01[2] = 0;
        int[] addend02 = new int[arraySizeTest];
        addend02[0] = 9;
        addend02[1] = 9;
        addend02[2] = 0;

        int[] actualResult = testSubject.sum(addend01, addend02);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 1;
        expectedResult[1] = 9;
        expectedResult[2] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }
}
