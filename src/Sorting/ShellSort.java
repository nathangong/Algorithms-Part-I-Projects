package Sorting;

public class ShellSort {
    public static void sort(Comparable[] arr) {
        int h = 1;
        while (h+2 < arr.length) {
            h += 2;
        }
        while (h >= 1) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = i; j > 0; j -= h) {
                    if (arr[j].compareTo(arr[j-1]) < 0) {
                        Comparable temp = arr[j];
                        arr[j] = arr[j-1];
                        arr[j-1] = temp;
                    }
                    else break;
                }
            }
            h -= 2;
        }
    }
}
