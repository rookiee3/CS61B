package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int topSite;
    private int bottomSite;
    private int openNum = 0;
    private boolean openFlag[][];
    private WeightedQuickUnionUF sites1;
    private WeightedQuickUnionUF sites2;

    private int xyTo1D (int row, int col) {
        return row * N + col;
    }
    private void valid(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }
    private void unionNeighbor (int row, int col, int newRow, int newCol) {
        if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
            return;
        }
        if (openFlag[newRow][newCol]){
            sites1.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
            sites2.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
        }
    }
    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("invalid N");
        }

        this.N = N;
        topSite = N * N;
        bottomSite = N * N + 1;

        sites1 = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            sites1.union(topSite, xyTo1D(0, i));
        }
        for (int i = 0; i < N; i++) {
            sites1.union(bottomSite, xyTo1D(N - 1, i));
        }

        //the set to check if is full
        sites2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            sites2.union(topSite, xyTo1D(0, i));
        }

        openFlag = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                openFlag[i][j] = false;
            }
        }
    }
    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        valid(row, col);
        if (openFlag[row][col]) {
            return;
        }
        openFlag[row][col] = true;
        openNum++;
        unionNeighbor(row, col, row - 1, col);
        unionNeighbor(row, col, row + 1, col);
        unionNeighbor(row, col, row, col - 1);
        unionNeighbor(row, col, row, col + 1);
    }
    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        valid(row, col);
        return openFlag[row][col];
    }
    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        valid(row, col);
        return openFlag[row][col] && sites2.connected(xyTo1D(row, col), topSite);
    }
    public int numberOfOpenSites() {
        // number of open sites
        return openNum;
    }
    public boolean percolates() {
        // does the system percolate?
        if (openNum == 0) {
            return false;
        }
        return sites1.connected(topSite, bottomSite);
    }
    public static void main(String[] args) {
        // use for unit testing (not required)
    }
}
