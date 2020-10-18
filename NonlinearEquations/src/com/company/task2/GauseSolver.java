package com.company.task2;

import java.util.Arrays;

public class GauseSolver implements SLAESolver {


    @Override
    public double[] solve(double[][] a, double[] b) {
        a = doForward(a,b);
        return doBackward(a, b);
    }

    private double[][] doForward(double[][] a, double[] b) {

        for (int k = 0; k < a[0].length - 1; k++) {
            for (int i = k + 1; i < a.length; i++) {
                var l = a[i][k] / a[k][k];
                for (int j = 0; j < a.length; j++) {
                    a[i][j] -= l * a[k][j];
                }
                b[i] -= l * b[k];
            }
        }
        return a;
    }

    private double[] doBackward(double[][] a, double[] b){
        var ans = new double[b.length];

        for (int i = b.length-1; i >= 0; i--) {
            for (int j = i+1; j < b.length; j++) {
                b[i] -= a[i][j] * ans[j];
            }
            ans[i] = 1./a[i][i] * b[i];
        }

        return ans;
    }
}
