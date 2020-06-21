package it.register.tech.exercise.two;

public class MathProductCalculator {

    public int[] multiply(int[] factor01, int[] factor02) {


        if (isZero(factor01) && isZero(factor02)) {
            return null; //TODO: is undefined
        } else if (isZero(factor01) || isZero(factor02)) {
            return new int[32];
        } else {

            return multiplyRecursiveStep(new int[32], factor01, factor02);

        }
    }

    private int[] multiplyRecursiveStep(int[] result, int[] factor01, int[] factor02) {

        if (isZero(factor02)) {
            return result;
        } else {
            result = sum(result, factor01);
            factor02 = subtractOne(factor02);
            return multiplyRecursiveStep(result, factor01, factor02);
        }
    }

    private boolean isOne(int[] factor02) {

        for (int i = 0; i < factor02.length; i++) {

            if (i == 0) {
                if (factor02[i] != 1)
                    return false;
            } else {
                if (factor02[i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isZero(int[] value) {

        for (int i = 0; i < value.length; i++) {
            if (value[i] != 0) {
                return false;
            }
        }
        return true;
    }
//
//        int count = calculateSumCount(factor02);
//
//        if (count == 0) {
//            return result;
//        } else if (count == 1) {
//            return factor01;
//        } else {
//
//            for (int i = 0; i < count; i++) {
//                result = sum(result, factor01);
//            }
//            return result;
//        }


    public int[] sum(int[] addend01, int[] addend02) {

        int[] result = new int[32];

        for (int i = 0; i < addend01.length; i++) {

            if (result[i] + addend01[i] + addend02[i] - 10 >= 0) {
                result[i] = result[i] + addend01[i] + addend02[i] - 10;
                result[i + 1]++;
            } else {
                result[i] = result[i] + addend01[i] + addend02[i];
            }
        }
        return result;
    }


    private int calculateSumCount(int[] factor02) {
        int count = 0;
        for (int i = 0; i < factor02.length; i++) {
            count = count + factor02[i] * ((int) Math.pow(10, i));
        }
        return count;
    }

    public int[] subtractOne(int[] value01) {

        int[] one = new int[32];
        one[0] = 1;
        int[] result = new int[32];

        for (int i = 0; i < value01.length; i++) {

            if (value01[i] != 0) {
                result[i] = value01[i] - one[i];
            } else if (value01[i] == 0 && one[i] == 0) {
                result[i] = 0;
            } else {
                result[i] = 9;
                if (i < 31) {
                    one[i + 1] = 1;
                }
            }
        }
        return result;
    }

    public int[] factorial(int[] value) {

        if (isZero(value)) {
            return null;
        } else {
            return factorialRecursiveStep(value);
        }
    }


    private int[] factorialRecursiveStep(int[] value) {

        if (isZero(value)) {
            int[] one = new int[32];
            one[0] = 1;
            return one;
        } else {
            int[] a = factorialRecursiveStep(subtractOne(value));
            int[] result = multiply(value, a);
            return result;
        }
    }
}
