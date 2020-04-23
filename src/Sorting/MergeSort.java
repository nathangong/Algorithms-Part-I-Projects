package Sorting;

public class MergeSort {
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

    public static void sort(Comparable[] arr, Comparable[] aux, int low, int high) {
        if (high <= low) return;
        int mid = low + (high-low)/2;
        sort(arr, aux, low, mid);
        sort(arr, aux, mid+1, high);
        if (arr[mid+1].compareTo(arr[mid]) >= 0) return;
        merge(arr, aux, low, mid, high);
    }

    public static void sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length-1);
    }
}
