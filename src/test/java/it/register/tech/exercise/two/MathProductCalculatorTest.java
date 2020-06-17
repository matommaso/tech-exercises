package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MathProductCalculatorTest {

    private MathProductCalculator testSubject = new MathProductCalculator();

    @Test
    void shouldAddAddendsWithNoAmountCarriedOver() {

        int[] addend01 = new int[32];
        addend01[0] = 2;
        addend01[1] = 1;

        int[] actualResult = testSubject.sum(addend01, addend01);
        int[] expectedResult = new int[32];
        expectedResult[0] = 4;
        expectedResult[1] = 2;

        assertThat(actualResult, equalTo(expectedResult));

    }

    @Test
    void shouldAddAddendsWithAmountCarriedOver() {

        int[] addend01 = new int[32];
        addend01[0] = 2;
        addend01[1] = 1;
        int[] addend02 = new int[32];
        addend02[0] = 9;
        addend02[1] = 1;

        int[] actualResult = testSubject.sum(addend01, addend02);
        int[] expectedResult = new int[32];
        expectedResult[0] = 1;
        expectedResult[1] = 3;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldAddAddendsWithASequenceOfAmountCarriedOver() {

        int[] addend01 = new int[32];
        addend01[0] = 2;
        addend01[1] = 9;
        addend01[2] = 0;
        int[] addend02 = new int[32];
        addend02[0] = 9;
        addend02[1] = 9;
        addend02[2] = 0;

        int[] actualResult = testSubject.sum(addend01, addend02);
        int[] expectedResult = new int[32];
        expectedResult[0] = 1;
        expectedResult[1] = 9;
        expectedResult[2] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyAFactorWithZero() {

        int[] factor01 = new int[32];
        factor01[0] = 2;
        factor01[1] = 1;

        int count = 0;

        int[] actualResult = testSubject.multiply(factor01, count);
        int[] expectedResult = new int[32];

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyAFactorWithOne() {

        int[] factor01 = new int[32];
        factor01[0] = 2;
        factor01[1] = 1;

        int count = 1;

        int[] actualResult = testSubject.multiply(factor01, count);
        int[] expectedResult = new int[32];
        expectedResult[0] = 2;
        expectedResult[1] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }


    @Test
    void shouldMultiplyA2DigitFactorWithASingleDigitFactor() {

        int[] factor01 = new int[32];
        factor01[0] = 2;
        factor01[1] = 1;

        int count = 2;

        int[] actualResult = testSubject.multiply(factor01, count);

        int[] expectedResult = new int[32];
        expectedResult[0] = 4;
        expectedResult[1] = 2;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyA2DigitFactorWithA2DigitFactor() {

        int[] factor01 = new int[32];
        factor01[0] = 2;
        factor01[1] = 1;

        int count = 10;

        int[] actualResult = testSubject.multiply(factor01, count);
        int[] expectedResult = new int[32];
        expectedResult[0] = 0;
        expectedResult[1] = 2;
        expectedResult[2] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyA3DigitFactorWithA3DigitFactor() {

        int[] factor01 = new int[32];
        factor01[0] = 9;
        factor01[1] = 9;
        factor01[2] = 9;

        int count = 123;

        int[] actualResult = testSubject.multiply(factor01, count);
        int[] expectedResult = new int[32];
        expectedResult[0] = 7;
        expectedResult[1] = 7;
        expectedResult[2] = 8;

        expectedResult[3] = 2;
        expectedResult[4] = 2;
        expectedResult[5] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

}
