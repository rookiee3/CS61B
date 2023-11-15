package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    //grid size
    private int N;
    private final int BLANK = 0;
    private int[][] tiles = new int[N][N];

    public Board(int[][] tiles) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i >= N && i < 0 && j >= N && j < 0) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int N = size();
        int blankXPos = -1;
        int blankYPos = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    blankXPos = i;
                    blankYPos = j;
                }
            }
        }

        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = tileAt(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((Math.abs(i - blankXPos) + Math.abs(j - blankYPos) - 1) == 0) {
                    temp[blankXPos][blankYPos] = temp[i][j];
                    temp[i][j] = BLANK;
                    Board neighbor = new Board(temp);
                    neighbors.enqueue(neighbor);
                    temp[i][j] = temp[blankXPos][blankYPos];
                    temp[blankXPos][blankYPos] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private int xyTo1D (int x, int y) {
        return x * N + y + 1;
    }
    public int hamming() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != xyTo1D(i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private int[] intToXY(int s) {
        return new int[] {(s - 1) / N, (s - 1) % N};
    }
    private int getXYdiff(int s, int row, int column)  {
        int res = 0;
        int[] rightPos = intToXY(s);
        res += rightPos[0] > row ? rightPos[0] - row : row - rightPos[0];
        res += rightPos[1] > column ? rightPos[1] - column : column - rightPos[1];
        return res;
    }
    public int manhattan() {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != xyTo1D(i, j)) {
                    res += getXYdiff(tileAt(i, j), i, j);
                }
            }
        }
        return res;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        Board b = (Board) y;
        if (this.N != b.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != b.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
