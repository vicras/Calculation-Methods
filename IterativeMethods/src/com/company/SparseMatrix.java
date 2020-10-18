package com.company;

import java.util.Collections;

public class SparseMatrix implements Matrixable{
    double[][] matrix;

    /**
     * (* 0 * 0 0)
     * (0 * 0 * 0)
     * (0 0 * 0 *)
     * (0 0 0 * 0)
     * (0 0 0 0 *)
     *
     * @param matrix
     */
    public SparseMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double get(int i, int j) {
        if(i>=getLength() || j >= getLength())
            throw new IndexOutOfBoundsException();
        if (j < i) {
            int t = i;
            i = j;
            j = t;
        }

        if (j == i) {
            return matrix[1][i];
        }

        if (j - i == 2) {
            return matrix[0][j - 1];
        }

        return 0.0;
    }

    public boolean set(int i, int j, double value) {
        doSet(i, j, value);
        return doSet(j, i, value);
    }

    public int getLength() {
        return matrix.length;
    }

    private boolean doSet(int i, int j, double value) {
        if (i == j) {
            matrix[1][i] = value;
            return true;
        }

        if (j - i == 2) {
            matrix[0][j - 1] = value;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {

        var ans = new StringBuilder();

        for (int i = 0; i < getLength(); i++) {
            for (int j = 0; j < getLength(); j++) {
                ans.append(get(i, j)).append("\t");
            }
            ans.append("\n");
        }

        return ans.toString();
    }
}
