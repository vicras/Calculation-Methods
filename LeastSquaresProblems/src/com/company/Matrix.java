package com.company;

import java.util.Arrays;

public class Matrix {
    double[][] matrix;

    Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    static Matrix transposeMatrix(Matrix a) {
        var ans = new double[a.matrix[0].length][a.matrix.length];

        for (int i = 0; i < a.getI(); i++) {
            for (int j = 0; j < a.getJ(); j++) {
                ans[j][i] = a.matrix[i][j];
            }
        }
        return new Matrix(ans);
    }

    static Matrix choleskyDecomposition(Matrix a) {
        var ans = new double[a.getI()][a.getJ()];
        for (int i = 0; i < a.getI(); i++)
        {
            double temp;
            for (int j = 0; j < i; j++)
            {
                temp = 0;
                for (int k = 0; k < j; k++)
                {
                    temp += ans[i][k] * ans[j][k];
                }
                ans[i][j] = (a.matrix[i][j] - temp) / ans[j][j];
            }
            temp = a.matrix[i][i];
            for (int k = 0; k < i; k++)
            {
                temp -= ans[i][k] * ans[i][k];
            }
            ans[i][i] = Math.sqrt(temp);
        }
        return new Matrix(ans);
    }

    static double[] solveCholesky(Matrix u, Matrix b) {
        var y = solveStraightCholesky(u, b);
        return solveTransposedCholesky(transposeMatrix(u), y);
    }

    private static double[] solveStraightCholesky(Matrix u, Matrix b) {
        var ans = new double[u.getI()];
        for (int i = 0; i < u.getI(); i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += u.matrix[i][j] * ans[j];
            }
            ans[i] = (b.matrix[i][0] - sum) / u.matrix[i][i];
        }
        return ans;
    }

    private static double[] solveTransposedCholesky(Matrix u, double[] y) {
        var ans = new double[u.getI()];
        for (int i = u.getI() - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = u.getJ() - 1; j > i; j--) {
                sum += u.matrix[i][j] * ans[j];
            }
            ans[i] = (y[i] - sum) / u.matrix[i][i];
        }
        return ans;
    }


    static Matrix multiplyMatrix(Matrix a, Matrix b) {
        if (a.getJ() != b.getI())
            throw new RuntimeException("Matrix can't be multiply (sizes don't match)");

        double[][] ans = new double[a.getI()][b.getJ()];


        for (int i = 0; i < a.getI(); i++) {
            for (int j = 0; j < b.getJ(); j++) {
                for (int k = 0; k < b.getI(); k++) {
                    ans[i][j] += a.matrix[i][k] * b.matrix[k][j];
                }
            }
        }

        return new Matrix(ans);
    }

    static double countInfelicity(double[] ax, double[] bx ){
        if(ax.length!=bx.length)
            throw new RuntimeException("ax.length!= bx.length");
        double sum=0;
        for(int i=0;i<ax.length;i++){
            sum+=(ax[i]-bx[i])*(ax[i]-bx[i]);
        }
        return Math.sqrt(sum);
    }

    public int getI() {
        return matrix.length;
    }

    public int getJ() {
        return matrix[0].length;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append("Matrix{\n");
        for (var i : matrix)
            ans.append("\t").append(Arrays.toString(i)).append("\n");
        ans.append('}');
        return ans.toString();
    }
}
