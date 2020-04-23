package PriorityQueues;

public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        N = 0;
    }

    public boolean isEmpty() { return N == 0; }

    public void insert(Key v) { pq[N++] = v; }

    public Key removeMax() {
        int maxIndex = 0;
        for (int i = 1; i < N; i++) {
            if (pq[i].compareTo(pq[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        Key temp = pq[maxIndex];
        pq[maxIndex] = pq[--N];
        pq[N] = temp;
        return pq[N];
    }
}
