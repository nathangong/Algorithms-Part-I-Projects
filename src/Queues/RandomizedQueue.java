package Queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;
    private int backIndex;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        size = 0;
        backIndex = -1;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        backIndex++;
        if (backIndex == arr.length) {
            Item[] newArr = (Item[]) new Object[arr.length * 2];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[backIndex] = item;
        size++;
    }

    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();

        int index = StdRandom.uniform(size);
        Item item = arr[index];
        if (index != size-1) arr[index] = arr[size-1];
        else arr[index] = null;
        backIndex--;
        size--;
        return item;
    }

    public Item sample() {
        int index = StdRandom.uniform(size);
        return arr[index];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int index = 0;

            public boolean hasNext() { return index < size; }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();

                if (index == 0) {
                    StdRandom.shuffle(arr, 0, size);
                }
                return arr[index++];
            }
        };
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        StdOut.println(queue.isEmpty());
        StdOut.println(queue.size());
        queue.enqueue(0);
        queue.sample();
        queue.dequeue();
    }
}
