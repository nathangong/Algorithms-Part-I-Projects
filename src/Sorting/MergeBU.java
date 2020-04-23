package Sorting;

public class MergeBU {
    public static void merge(Comparable[] arr, Comparable[] aux, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = arr[i];
        }
        int i = low;
        int j = mid+1;
        for (int k = low; k <= high; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > high) arr[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) <= 0) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }

    public static void sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        for (int i = 1; i < arr.length; i=i*2) {
            for (int j = 0; j < arr.length-i; j+=i*2) {
                merge(arr, aux, j, j+i-1, Math.min(j+i+i-1, arr.length-1));
            }
        }
    }
}
