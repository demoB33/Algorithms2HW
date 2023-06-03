package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void sortBubble(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minElementIndex];
            arr[minElementIndex] = temp;
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }


    public static void main(String[] args) {

        Random random = new Random();
        int[] arr1 = new int[100_000];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt(1000);
        }
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        int[] arr3 = Arrays.copyOf(arr1, arr1.length);
        int[] arr4 = Arrays.copyOf(arr1, arr1.length);


        long start = System.currentTimeMillis();
        sortBubble(arr1);
        long time = System.currentTimeMillis() - start;
        System.out.println("sortBubble: " + time);


        start = System.currentTimeMillis();
        sortSelection(arr1);
        time = System.currentTimeMillis() - start;
        System.out.println("sortSelection: " + time);

        start = System.currentTimeMillis();
        sortInsertion(arr1);
        time = System.currentTimeMillis() - start;
        System.out.println("sortInsertion: " + time);



    }

}