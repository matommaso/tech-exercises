package it.register.tech.exercise.two;

public class MathematicalCalculator {

    private final int arraySize;

    public MathematicalCalculator(int arraySize) {
        this.arraySize = arraySize;
    }

    public int[] multiply(int[] factor01, int[] factor02) {

        int[] result = new int[arraySize];

        while (!isEqualToZero(factor02)) {
            result = sum(result, factor01);
            factor02 = subtractOne(factor02);
        }
        return result;
    }

    private boolean isEqualToZero(int[] value) {

        for (int i = 0; i < value.length; i++) {
            if (value[i] != 0)
                return false;
        }
        return true;
    }

    public int[] sum(int[] addend01, int[] addend02) {

        int[] result = new int[arraySize];

        for (int i = 0; i < addend01.length; i++) {

            int partialSum = result[i] + addend01[i] + addend02[i];
            if (partialSum - 10 >= 0) {
                result[i] = partialSum - 10;
                result[i + 1]++;
            } else {
                result[i] = partialSum;
            }
        }
        return result;
    }

    public int[] subtractOne(int[] value) {

        int[] result = new int[arraySize];
        int[] one = new int[arraySize];
        one[0] = 1;

        for (int i = 0; i < value.length; i++) {

            result[i] = result[i] + value[i] - one[i];

            if (result[i] < 0) {
                result[i] = 9;
                result[i + 1] = result[i + 1] - 1;
            }
        }
        return result;
    }

    public int[] factorial(int[] value) {

        int[] result = value;

        if (isEqualToZero(value)) {
            result[0] = 1;
        } else {
            while (!isEqualToZero(subtractOne(value))) {
                result = multiply(result, subtractOne(value));
                value = subtractOne(value);
            }
        }
        return result;
    }
}
