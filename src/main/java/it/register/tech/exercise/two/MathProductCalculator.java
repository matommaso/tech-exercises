package it.register.tech.exercise.two;

public class MathProductCalculator {

    public int[] sum(int[] addend01, int[] addend02) {

        int[] result = new int[32];

        for (int i = 0; i < addend01.length; i++) {

            if (result[i] + addend01[i] + addend02[i] - 10 >= 0) {
                result[i] = result[i] + addend01[i] + addend02[i] - 10;
                result[i + 1]++;
            } else {
                result[i] = result[i]+ addend01[i] + addend02[i];
            }
        }
        return result;
    }

    public int[] multiply(int[] factor01, int count) {

        int[] result = new int[32];

        if (count == 0) {
            return result;
        } else if (count == 1) {
            return factor01;
        } else {

            for (int i = 0; i < count; i++) {
                result = sum(result, factor01);
            }
            return result;
        }
    }
}
