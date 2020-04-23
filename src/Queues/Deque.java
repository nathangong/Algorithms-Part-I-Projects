package Queues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Item[] arr;
    private int frontIndex;
    private int backIndex;
    private int size;

    public Deque() {
        arr = (Item[]) new Object[1];
        frontIndex = 1;
        backIndex = 0;
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        size++;
        if (size > arr.length) {
            Item[] newArr = (Item[]) new Object[arr.length*2];
            int index = 0;
            for (Item i : this) {
                newArr[index] = i;
                index++;
            }
            frontIndex = 0;
            backIndex = arr.length-1;
            arr = newArr;
        }
        if (frontIndex == 0) frontIndex = arr.length-1;
        else frontIndex--;

        arr[frontIndex] = item;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        size++;
        if (size == 1) frontIndex = 0;
        if (size > arr.length) {
            Item[] newArr = (Item[]) new Object[arr.length*2];
            int index = 0;
            for (Item i : this) {
                newArr[index] = i;
                index++;
            }
            frontIndex = 0;
            backIndex = arr.length-1;
            arr = newArr;
        }
        if (backIndex == arr.length-1) backIndex = 0;
        else backIndex++;
        arr[backIndex] = item;
    }

    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        if (size == arr.length/4) {
            Item[] newArr = (Item[]) new Object[arr.length/2];
            int firstRealIndex = 0;
            while (arr[firstRealIndex] == null) {
                firstRealIndex++;
            }
            for (int i = firstRealIndex; i < arr.length && arr[i] != null; i++) {
                newArr[i-firstRealIndex] = arr[i];
            }
            backIndex -= firstRealIndex;
            frontIndex -= firstRealIndex;
            arr = newArr;
        }

        Item item = arr[frontIndex];
        arr[frontIndex] = null;
        frontIndex = (frontIndex + 1) % arr.length;
        size--;
        return item;
    }

    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();

        if (size == arr.length/4) {
            Item[] newArr = (Item[]) new Object[arr.length/2];
            int firstRealIndex = 0;
            while (arr[firstRealIndex] == null) {
                firstRealIndex++;
            }
            for (int i = firstRealIndex; i < arr.length && arr[i] != null; i++) {
                newArr[i-firstRealIndex] = arr[i];
            }
            backIndex -= firstRealIndex;
            frontIndex -= firstRealIndex;
            arr = newArr;
        }

        Item item = arr[backIndex];
        arr[backIndex] = null;
        backIndex--;
        if (backIndex < 0) backIndex += arr.length;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int index = frontIndex;
            private boolean hasNext = true;

            public boolean hasNext() {
                if (size == 0) return false;
                return hasNext;
            }

            public Item next() {
                if (size == 0) hasNext = false;
                if (!hasNext) throw new NoSuchElementException();

                if (index == backIndex) hasNext = false;
                Item item = arr[index];
                index = (index + 1) % arr.length;
                return item;
            }

            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
}
