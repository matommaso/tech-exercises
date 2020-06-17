package it.register.tech.exercise.two;

public class MathProductCalculator {

    public int[] sum(int[] addend01, int[] addend02) {

        int[] result = new int[addend01.length];

        for (int i = addend01.length - 1; i >= 0; i--) {

            result[i] = (result[i] + addend01[i] + addend02[i]) %10;

            if(result[i] <= addend01[i] || result[i] <= addend02[i]){
                result[i-1] ++;
            }
        }
        return result;
    }

    public int[] multiply(int[] factor01, int[] factor02) {

    return null;


    }
}
