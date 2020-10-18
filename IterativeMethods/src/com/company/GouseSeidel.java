package com.company;

import java.util.Arrays;

public class GouseSeidel {

    private final int MAX_ITERATION;
    private final double EPSILON;
    private final double[][] a;
    private final double[] b;

    public GouseSeidel(int maxIteration, double epsilon, double[][] a, double[] b) {
        this.a = a;
        this.b = b;
        this.EPSILON = epsilon;
        this.MAX_ITERATION = maxIteration;
    }

    //region straight

    public double[] countStraight(double[] startX) {

        var ans = Arrays.copyOf(startX, startX.length);

        for (int i = 0; i < MAX_ITERATION && !isConverge(ans); i++) {
            ans = straightIteration(ans);
        }

        return ans;
    }

    /**
     * @return new x values
     */
    private double[] straightIteration(double[] previousValues) {
        double[] ans = new double[previousValues.length];

        for (int i = 0; i < a.length; i++) {

            ans[i] = 1.0 / a[i][i] * (b[i] - getSumBefore(ans, i) - getSumAfter(previousValues, i));

        }

        return ans;
    }

    private double getSumBefore(double[] newValues, int iteration) {
        var sum = 0.0;
        for (int i = 0; i < iteration; i++) {
            sum+= a[iteration][i] * newValues[i];
        }
        return sum;
    }

    private double getSumAfter(double[] previousValues, int iteration){
        var sum = 0.0;
        for (int i = iteration +1; i < previousValues.length; i++) {
            sum += a[iteration][i] * previousValues[i];
        }
        return sum;
    }

    //endregion

    //region reverse
    public double[] countReverse(double[] startX) {

        var ans = Arrays.copyOf(startX, startX.length);

        for (int i = 0; i < MAX_ITERATION && !isConverge(ans); i++) {

            ans = reverseIteration(ans);

        }

        return ans;
    }

    private double[] reverseIteration(double[] previousValues) {
        double[] ans = new double[previousValues.length];

        for (int i = previousValues.length -1; i >=0 ; i--) {

            double sumBeforeReverse = getSumBefore(previousValues, i);
            double sumAfterReverse = getSumAfter(ans, i);

            ans[i] = 1.0 / a[i][i] * (b[i] - sumBeforeReverse - sumAfterReverse);

        }

        return ans;
    }


    //endregion

    //region symmetric

    public double[] countSymmetric(double[] startX){

        var ans = Arrays.copyOf(startX, startX.length);

        for (int i = 0; i < MAX_ITERATION && !isConverge(ans); i++) {
            ans = straightIteration(ans);
            ans = reverseIteration(ans);
        }

        return ans;
    }

    //endregion

    //region isConverge

    private boolean isConverge(double[] x){
        double[] axMatrix = multiplyMatrix(a, x);
        double infelicity = countInfelicity(axMatrix, b);
        return infelicity < EPSILON;
    }

    private static double[] multiplyMatrix(double[][] a, double[] b) {
        var ans = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            double sum = 0;
            for (int j = 0; j < a[0].length; j++) {
                sum += a[i][j] * b[j];
            }
            ans[i] = sum;
        }
        return (ans);
    }

    private static double countInfelicity(double[] ax, double[] bx ){
        if(ax.length!=bx.length)
            throw new RuntimeException("ax.length!= bx.length");
        double sum=0;
        for(int i=0;i<ax.length;i++){
            sum+=(ax[i]-bx[i])*(ax[i]-bx[i]);
        }
        return Math.sqrt(sum);
    }

    //endregion
}
