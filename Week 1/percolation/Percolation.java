/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[] openSites;
    private int numOpenSites = 0;

    private int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than 0!");

        size = n;
        openSites = new boolean[n * n];
        // 0 is virtual start site
        uf = new WeightedQuickUnionUF(n * n + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        assertOutOfRange(row, col);

        if (isOpen(row, col)) return;
        int i = mapSiteIndex(row, col);
        // Open site
        openSites[i] = true;
        numOpenSites++;

        // Check to connect virtual start site
        if (i < size && isOpen(row, col))
            uf.union(0, i + 1);

        // Check to connect top of site
        if (row > 1 && isOpen(row - 1, col))
            uf.union(i + 1, mapSiteIndex(row - 1, col) + 1);
        // Check to connect left of site
        if (col > 1 && isOpen(row, col - 1))
            uf.union(i + 1, mapSiteIndex(row, col - 1) + 1);
        // Check to connect right of site
        if (col < size && isOpen(row, col + 1))
            uf.union(i + 1, mapSiteIndex(row, col + 1) + 1);
        // Check to connect bottom of site
        if (row < size && isOpen(row + 1, col))
            uf.union(i + 1, mapSiteIndex(row + 1, col) + 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        assertOutOfRange(row, col);

        return openSites[mapSiteIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        assertOutOfRange(row, col);

        return uf.find(0) == uf.find(mapSiteIndex(row, col) + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // boolean percolate = false;
        // for (int i = size * (size - 1); i < size * size; i++) {
        //     if (!openSites[i]) continue;
        //     percolate = uf.find(0) == uf.find(i + 1);
        //     if (percolate) break;
        // }
        // return percolate;
        return uf.find(0) == uf.find(openSites.length + 1);
    }

    private int mapSiteIndex(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    private void assertOutOfRange(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException(
                    "row or col or both are outside its prescribed range!");
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
