package SliderPuzzle;

import java.util.LinkedList;

public class Board {
    private int[][] tiles;

    public Board(int[][] tiles) {
        this.tiles = copy(tiles);
    }

    private int[][] copy(int[][] tiles) {
        int[][] tilesCopy = new int[tiles.length][tiles.length];
        for (int i = 0; i < tilesCopy.length; i++) {
            for (int j = 0; j < tilesCopy[i].length; j++){
                tilesCopy[i][j] = tiles[i][j];
            }
        }
        return tilesCopy;
    }

    @Override
    public String toString() {
        String str = "";
        str += dimension() + "\n";
        for (int i = 0; i < tiles.length; i++) {
            str += " ";
            for (int j = 0; j < tiles[i].length; j++) {
                if (j == tiles.length-1) str += tiles[i][j] + "\n";
                else str += tiles[i][j] + " ";
            }
        }
        return str;
    }

    public int dimension() { return tiles.length; }

    public int hamming() {
        int count = 0;

        int curr = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != curr) {
                    count++;
                }
                curr++;
            }
        }
        return count;
    }

    public int manhattan() {
        int total = 0;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) continue;
                int cRow = tiles[i][j] / dimension();
                int cCol = tiles[i][j] % dimension() - 1;
                if (cCol == -1) {
                    cRow--;
                    cCol = dimension()-1;
                }
                total += Math.abs(i-cRow) + Math.abs(j-cCol);
            }
        }
        return total;
    }

    public boolean isGoal() {
        int curr = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != curr) return false;
                curr = (curr + 1) % (dimension() * dimension());
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Board)) return false;

        Board that = (Board) o;
        if (this.dimension() != that.dimension()) return false;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbors = new LinkedList<Board>();

        int i = -1;
        int j = -1;
        
        outerloop:
        for (i = 0; i < tiles.length; i++) {
            for (j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) break outerloop;
            }
        }

        if (i + 1 < tiles.length) {
            int[][] tilesCopy = copy(tiles);
            tilesCopy[i][j] = tilesCopy[i+1][j];
            tilesCopy[i+1][j] = 0;
            neighbors.add(new Board(tilesCopy));
        }
        if (i - 1 >= 0) {
            int[][] tilesCopy = copy(tiles);
            tilesCopy[i][j] = tilesCopy[i-1][j];
            tilesCopy[i-1][j] = 0;
            neighbors.add(new Board(tilesCopy));
        }
        if (j + 1 < tiles.length) {
            int[][] tilesCopy = copy(tiles);
            tilesCopy[i][j] = tilesCopy[i][j+1];
            tilesCopy[i][j+1] = 0;
            neighbors.add(new Board(tilesCopy));
        }
        if (j - 1 >= 0) {
            int[][] tilesCopy = copy(tiles);
            tilesCopy[i][j] = tilesCopy[i][j-1];
            tilesCopy[i][j-1] = 0;
            neighbors.add(new Board(tilesCopy));
        }

        return neighbors;
    }

    public Board twin() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length-1; j++) {
                if (tiles[i][j] != 0 && tiles[i][j+1] != 0) {
                    int[][] tilesCopy = copy(tiles);
                    int temp = tilesCopy[i][j];
                    tilesCopy[i][j] = tilesCopy[i][j+1];
                    tilesCopy[i][j+1] = temp;
                    return new Board(tilesCopy);
                }
            }
        }

        throw new RuntimeException();
    }
}
