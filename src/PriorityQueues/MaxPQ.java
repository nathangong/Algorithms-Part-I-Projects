package PriorityQueues;

import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MaxPQ() {
        pq = (Key[]) new Comparable[2];
        N = 0;
    }

    public void insertKey(Key key) {
        N++;
        if (N >= pq.length) {
            Key[] newPQ = (Key[]) new Comparable[pq.length*2];
            for (int i = 1; i < pq.length; i++) newPQ[i] = pq[i];
            pq = newPQ;
        }

        pq[N] = key;
        swim(N);
    }

    public Key removeMax() {
        if (isEmpty()) throw new NoSuchElementException();

        N--;
        if (N <= pq.length/4) {
             Key[] newPQ = (Key[]) new Comparable[pq.length/2];
             for (int i = 1; i <= N; i++) newPQ[i] = pq[i];
             pq = newPQ;
        }

        Key max = pq[0];
        pq[0] = pq[N+1];
        pq[N+1] = null;
        sink(0);
        return max;
    }

    public Key getMax(){ return pq[1]; }

    public boolean isEmpty() { return N == 0; }

    private void swim(int k) {
        while (k/2 != 0 && pq[k].compareTo(pq[k/2]) > 0) {
            Key temp = pq[k];
            pq[k] = pq[k/2];
            pq[k/2] = temp;
            k /= 2;
        }
    }

    private void sink(int k) {
        while (k*2 < N) {
            int j = k*2;
            if (j+1 <= N && pq[j+1].compareTo(pq[j]) > 0) j++;
            if (pq[k].compareTo(pq[j]) < 0) {
                Key temp = pq[k];
                pq[k] = pq[j];
                pq[j] = temp;
            }
            else break;
            k = j;
        }
    }
}
