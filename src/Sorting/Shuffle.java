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
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Shuffle.shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
}
