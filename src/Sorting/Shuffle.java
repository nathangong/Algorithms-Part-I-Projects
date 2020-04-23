package Sorting;

import java.util.Arrays;

public class Shuffle {
    public static void shuffle(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int randomIndex = (int) (Math.random()*(i+1));
            Object temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }
    }
}
