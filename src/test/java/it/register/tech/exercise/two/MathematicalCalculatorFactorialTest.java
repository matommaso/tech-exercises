package it.register.tech.exercise.two;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MathematicalCalculatorFactorialTest {

    private MathematicalCalculator testSubject = new MathematicalCalculator();


    @Test
    void shouldCalculateTheFactorialOfZero() {

        int[] value = new int[200];
        value[0] = 0;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = new int[200];
        expectedResult[0] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }


    @Test
    void shouldCalculateTheFactorialOfOne() {

        int[] value = new int[200];
        value[0] = 1;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = new int[200];
        expectedResult[0] = 1;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOfFour() {

        int[] value = new int[200];
        value[0] = 4;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = new int[200];
        expectedResult[0] = 4;
        expectedResult[1] = 2;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOfEleven() {

        int[] value = new int[200];
        value[0] = 1;
        value[1] = 1;

        int[] actualResult = testSubject.factorial(value);
        int[] expectedResult = new int[200];

        expectedResult[0] = 0;
        expectedResult[1] = 0;
        expectedResult[2] = 8;
        expectedResult[3] = 6;
        expectedResult[4] = 1;
        expectedResult[5] = 9;
        expectedResult[6] = 9;
        expectedResult[7] = 3;

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOf50() {

        int[] value = new int[200];
        value[0] = 0;
        value[1] = 5;

        int[] actualResult = testSubject.factorial(value);


        String factorial = "30414093201713378043612608166064768844377641568960512000000000000";

        int[] expectedResult = new int[200];
        for (int i = 0; i < factorial.length(); i++) {
            expectedResult[i] = Character.getNumericValue(factorial.charAt(factorial.length() - i - 1));
        }

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    void shouldCalculateTheFactorialOf100() {

        int[] value = new int[200];
        value[0] = 0;
        value[1] = 0;
        value[2] = 1;
        int[] actualResult = testSubject.factorial(value);


        String factorial = "93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000";

        int[] expectedResult = new int[200];
        for (int i = 0; i < factorial.length(); i++) {
            expectedResult[i] = Character.getNumericValue(factorial.charAt(factorial.length() - i - 1));
        }

        assertThat(actualResult, equalTo(expectedResult));
    }

}
