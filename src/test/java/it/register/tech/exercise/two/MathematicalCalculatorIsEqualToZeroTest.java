package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MathematicalCalculatorIsEqualToZeroTest {

    private int arraySizeTest = 200;
    private MathematicalCalculator testSubject = new MathematicalCalculator(arraySizeTest);

    @Test
    void shouldReturnFalseWhenTheValueIsNotEqualToZero() {

        int[] value = new int[arraySizeTest];
        value[0] = 1;

        boolean actualResult = testSubject.isEqualToZero(value);

        assertThat(actualResult, equalTo(false));
    }

    @Test
    void shouldReturnTrueWhenTheValueIsEqualToZero() {

        int[] value = new int[arraySizeTest];
        value[0] = 0;

        boolean actualResult = testSubject.isEqualToZero(value);

        assertThat(actualResult, equalTo(true));
    }
}
