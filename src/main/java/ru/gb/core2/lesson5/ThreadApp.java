package ru.gb.core2.lesson5;

import java.util.Arrays;

public class ThreadApp {
    public static void main(String[] args) {
    firstMethod();
    secondMethod();
    }

    public static void firstMethod() {
        int size = 10000000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() {
        int size = 10000000;
        final int HALF = size / 2;
        float[] arr2 = new float[size];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long startTime = System.currentTimeMillis();
        float[] leftHalf = new float[HALF];
        float[] rightHalf = new float[HALF];
        System.arraycopy(arr2, 0, leftHalf, 0, HALF);
        System.arraycopy(arr2, HALF, rightHalf, 0, HALF);
        float[] mergedArray = new float[size];
        System.arraycopy(leftHalf, 0, mergedArray, 0, HALF);
        System.arraycopy(rightHalf, 0, mergedArray, HALF, HALF);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
        System.out.println(Arrays.equals(arr2, mergedArray));

    }
}
