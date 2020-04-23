package SliderPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Node solved;

    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node prevNode;
        private int manhattan;

        public Node(Board board, Node prevNode) {
            if (board == null) throw new IllegalArgumentException();

            this.board = board;
            this.prevNode = prevNode;
            this.manhattan = board.manhattan();

            if (prevNode == null) moves = 0;
            else moves = prevNode.moves+1;
        }

        public int compareTo(Node that) {
            return (this.moves + this.manhattan) - (that.moves + that.manhattan);
        }
    }

    public Solver(Board initial) {
        MinPQ<Node> pq = new MinPQ<Node>();

        pq.insert(new Node(initial, null));
        while(true) {
            if (pq.isEmpty()) return;

            Node currNode = pq.delMin();
            if (currNode.board.isGoal()) {
                solved = currNode;
                return;
            }
            if (currNode.board.twin().isGoal()) return;

            for (Board b : currNode.board.neighbors()) {
                if (currNode.prevNode == null || !b.equals(currNode.prevNode.board)) {
                    pq.insert(new Node(b, currNode));
                }
            }
        }
    }

    public boolean isSolvable() { return solved != null; }

    public int moves() {
        if (solved == null) return -1;
        return solved.moves;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> moves = new Stack<Board>();
        Node currNode = solved;

        while (currNode != null) {
            moves.push(currNode.board);
            currNode = currNode.prevNode;
        }

        return moves;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
