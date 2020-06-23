package it.register.tech.exercise.two;

public class MathematicalCalculator {


    public int[] multiply(int[] factor01, int[] factor02) {

        int[] result = new int[200];

        while (!isZero(factor02)) {
            result = sum(result, factor01);
            factor02 = subtractOne(factor02);
        }
        return result;
    }


//    public int[] multiply(int[] factor01, int[] factor02) {
//
//        if (isZero(factor01) || isZero(factor02)) {
//            return new int[200];
//        } else {
//            return multiplyRecursiveStep(factor01, factor02);
//        }
//    }
//
//    private int[] multiplyRecursiveStep(int[] factor01, int[] factor02) {
//
//        if (isZero(factor02)) {
//            return factor02;
//        } else {
//            factor02 = subtractOne(factor02);
//            return sum(factor01, multiplyRecursiveStep(factor01, factor02));
//        }
//    }

    private boolean isZero(int[] value) {

        for (int i = 0; i < value.length; i++) {
            if (value[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public int[] sum(int[] addend01, int[] addend02) {

        int[] result = new int[200];

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

    public int[] subtractOne(int[] value01) {

        int[] result = new int[200];
        int[] one = new int[200];
        one[0] = 1;

        for (int i = 0; i < value01.length; i++) {

            result[i] = result[i] + value01[i] - one[i];

            if (result[i] < 0) {
                result[i] = 9;
                result[i + 1] = result[i + 1] - 1;
            }
        }
        return result;
    }

//        int[] one = new int[200];
//        one[0] = 1;
//        int[] result = new int[200];
//
//        for (int i = 0; i < value01.length; i++) {
//
//            if (value01[i] != 0) {
//                result[i] = value01[i] - one[i];
//            } else if (value01[i] == 0 && one[i] == 0) {
//                result[i] = 0;
//            } else {
//                result[i] = 9;
//                if (i < 31) {
//                    one[i + 1] = 1;
//                }
//            }
//        }
//        return result;
//    }

    public int[] factorial(int[] value) {

        int[] result = value;

        if (isZero(value)) {
            result[0] = 1;
        } else {
            while (!isZero(subtractOne(value))) {
                result = multiply(result, subtractOne(value));
                value = subtractOne(value);
            }
        }
        return result;
    }


//    public int[] factorial(int[] value) {
//
//        if (isZero(value)) {
//            int[] one = new int[200];
//            one[0] = 1;
//            return one;
//        } else {
//            int[] a = factorial(subtractOne(value));
//            int[] result = multiply(value, a);
//            return result;
//        }
//    }
}
