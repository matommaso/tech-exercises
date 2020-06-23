package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MathematicalCalculatorFactorialTest {

    private int arraySizeTest = 200;
    private MathematicalCalculator testSubject = new MathematicalCalculator(arraySizeTest);

    @Test
    void shouldCalculateTheFactorialOfZero() {

        int[] value = new int[arraySizeTest];
        value[0] = 0;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }


    @Test
    void shouldCalculateTheFactorialOfOne() {

        int[] value = new int[arraySizeTest];
        value[0] = 1;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOfFour() {

        int[] value = new int[arraySizeTest];
        value[0] = 4;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = new int[arraySizeTest];
        expectedResult[0] = 4;
        expectedResult[1] = 2;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOfEleven() {

        int[] value = new int[arraySizeTest];
        value[0] = 1;
        value[1] = 1;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = getNumber("39916800");

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOf50() {

        int[] value = new int[arraySizeTest];
        value[0] = 0;
        value[1] = 5;

        int[] actualResult = testSubject.factorial(value);

        int[] expectedResult = getNumber("30414093201713378043612608166064768844377641568960512000000000000");
        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOf100() {

        int[] value = new int[arraySizeTest];
        value[0] = 0;
        value[1] = 0;
        value[2] = 1;
        int[] actualResult = testSubject.factorial(value);

        int[] expectedResult = getNumber("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000");

        assertThat(actualResult, equalTo(expectedResult));
    }

    private int[] getNumber(String stringNumber) {

        int[] expectedResult = new int[arraySizeTest];
        for (int i = 0; i < stringNumber.length(); i++) {
            expectedResult[i] = Character.getNumericValue(stringNumber.charAt(stringNumber.length() - i - 1));
        }
        return expectedResult;
    }
}
