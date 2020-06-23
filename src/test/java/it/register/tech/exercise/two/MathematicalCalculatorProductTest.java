package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MathematicalCalculatorProductTest {

    private int arraySizeTest = 200;
    private MathematicalCalculator testSubject = new MathematicalCalculator(arraySizeTest);

    @Test
    void shouldMultiplyAFactorWithZero()  {

        int[] factor01 = new int[arraySizeTest];
        factor01[0] = 2;
        factor01[1] = 1;

        int[] factor02 = new int[arraySizeTest];

        int[] actualResult = testSubject.multiply(factor01, factor02);

        int[] expectedResult = new int[arraySizeTest];

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyAFactorWithOne()  {

        int[] factor01 = new int[arraySizeTest];
        factor01[0] = 2;
        factor01[1] = 1;

        int[] factor02 = new int[arraySizeTest];
        factor02[0] = 1;

        int[] actualResult = testSubject.multiply(factor01, factor02);

        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 2;
        expectedResult[1] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyAFactorWithOneCommutativeProperty()  {

        int[] factor01 = new int[arraySizeTest];
        factor01[0] = 1;

        int[] factor02 = new int[arraySizeTest];
        factor02[0] = 2;
        factor02[1]=1;

        int[] actualResult = testSubject.multiply(factor01, factor02);

        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 2;
        expectedResult[1] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyA2DigitFactorWithASingleDigitFactor()  {

        int[] factor01 = new int[arraySizeTest];
        factor01[0] = 2;
        factor01[1] = 1;

        int[] factor02 = new int[arraySizeTest];
        factor02[0] = 2;

        int[] actualResult = testSubject.multiply(factor01, factor02);

        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 4;
        expectedResult[1] = 2;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyA2DigitFactorWithA2DigitFactor()  {

        int[] factor01 = new int[arraySizeTest];
        factor01[0] = 2;
        factor01[1] = 1;

        int[] factor02 = new int[arraySizeTest];
        factor02[0] = 0;
        factor02[1] = 1;

        int[] actualResult = testSubject.multiply(factor01, factor02);

        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 0;
        expectedResult[1] = 2;
        expectedResult[2] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyA3DigitFactorWithA3DigitFactor()  {

        int[] factor01 = new int[arraySizeTest];
        factor01[0] = 9;
        factor01[1] = 9;
        factor01[2] = 9;

        int[] factor02 = new int[arraySizeTest];
        factor02[0] = 3;
        factor02[1] = 2;
        factor02[2] = 1;

        int[] actualResult = testSubject.multiply(factor01, factor02);

        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 7;
        expectedResult[1] = 7;
        expectedResult[2] = 8;

        expectedResult[3] = 2;
        expectedResult[4] = 2;
        expectedResult[5] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldMultiplyA3DigitFactorWithA3DigitFactorTEST()  {

        int[] factor01 = new int[arraySizeTest];
        factor01[0] = 9;

        int[] factor02 = new int[arraySizeTest];
        factor02[0] = 3;
        factor02[1] = 2;
        factor02[2] = 1;

        int[] actualResult = testSubject.multiply(factor01, factor02);

        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 7;
        expectedResult[1] = 0;
        expectedResult[2] = 1;
        expectedResult[3] = 1;

        assertThat(actualResult, equalTo(expectedResult));
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
    void shouldSubtractOne() {

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
    void shouldSubtractOneToANumberWithACarriedOver()   {

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
    void shouldSubtractOneToANumberWithMultipleCarriedOvers()   {

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
