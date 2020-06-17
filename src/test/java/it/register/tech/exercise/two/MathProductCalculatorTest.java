package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

class MathProductCalculatorTest {

    private MathProductCalculator testSubject = new MathProductCalculator();

    @Test
    void shouldAddAddendsWithNoAmountCarriedOver(){

        int [] addend01 = {1,2};
        int [] addend02 = {1,2};

        int[] actualResult = testSubject.sum(addend01,addend02);
        int [] expectedResult = {2,4};

        assertThat(actualResult, equalTo(expectedResult));

    }

    @Test
    void shouldAddAddendsWithAmountCarriedOver(){

        int [] addend01 = {1,2};
        int [] addend02 = {1,9};

        int[] actualResult = testSubject.sum(addend01,addend02);
        int [] expectedResult = {3,1};

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldAddAddendsWithASequenceOfAmountCarriedOver(){

        int [] addend01 = {0,9,2};
        int [] addend02 = {0,9,9};

        int[] actualResult = testSubject.sum(addend01,addend02);
        int [] expectedResult = {1,9,1};

        assertThat(actualResult, equalTo(expectedResult));
    }


    @Test
    void shouldMultiplyA2DigitFactorWithASingleDigitFactor(){

        int [] factor01 = {1,2};
        int [] factor02 = {2};

        int[] actualResult = testSubject.multiply(factor01,factor02);
        int [] expectedResult = {2,4};

        assertThat(actualResult, equalTo(expectedResult));
    }

}
