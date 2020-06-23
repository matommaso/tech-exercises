package it.register.tech.exercise.two.main;

import it.register.tech.exercise.two.MathematicalCalculator;

import java.math.BigInteger;

public class MathematicalCalculatorMain {

    public static void main(String[] args) {

        int arraySize = 200;
        int[] value = new int[arraySize];
        value[0] = 0;
        value[1] = 0;
        value[2] = 1;

        MathematicalCalculator mathematicalCalculator = new MathematicalCalculator(arraySize);
        int[] factorial = mathematicalCalculator.factorial(value);

        String factorialString = "";
        for(int i=factorial.length-1; i>=0; i--){
            factorialString += factorial[i];
        }

        System.out.println(new BigInteger(factorialString));

    }
}
