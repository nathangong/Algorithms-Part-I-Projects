package Sorting;

public class HeapSort {
    private static Comparable[] arr;

    public static void sort(Comparable[] input) {
        arr = input;
        for (int i = arr.length/2; i >= 1; i--) {
            sink(i, arr.length);
        }
        int n = arr.length;
        while(n > 1) {
            Comparable temp = arr[--n];
            arr[n] = arr[0];
            arr[0] = temp;
            sink(0, n);
        }

    }

    private static void sink(int k, int n) {
        while (k*2+1 < n) {
            int j = k*2+1;
            if (j+1 < n && arr[j+1].compareTo(arr[j]) > 0) j++;
            if (arr[k].compareTo(arr[j]) < 0) {
                Comparable temp = arr[k];
                arr[k] = arr[j];
                arr[j] = temp;
            }
            else break;
            k = j;
        }
    }
}
