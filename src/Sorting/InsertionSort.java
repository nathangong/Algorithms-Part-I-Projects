package Sorting;

public class InsertionSort {
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j-1]) < 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                else break;
            }
        }
    }
    public static void sort(Comparable[] arr, int low, int high) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low; j--) {
                if (arr[j].compareTo(arr[j-1]) < 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                else break;
            }
        }
    }
}
