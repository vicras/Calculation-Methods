package com.company;

import java.util.Arrays;

public class Main {

    //region init region
    private static double[][] TASK_A = new double[][]{
            {5, 3.0, 3.0, -4.0, 5.0, -5.0, -4.0, -5.0, 0.0, 2.0},
            {5.0, 3.0, -1.0, 2.0, 3.0, 0.0, -4.0, 1.0, -4.0, -5.0},
            {10.0, 6.0, 2.0, -3.0, -2.0, -2.0, -1.0, 0.0, -3.0, -5.0},
            {20.0, 12.0, 4.0, -5.0, -1.0, -4.0, 4.0, -2.0, -2.0, -4.0},
            {40.0, 24.0, 8.0, -10.0, 5.0, -1.0, 5.0, 2.0, 0.0, -3},
            {80.0, 48.0, 16.0, -20.0, 10.0, -12.0, -3.0, -3.0, 3.0, 2.0},
            {160.0, 96.0, 32.0, -40.0, 20.0, -24.0, -3.0, -4.0, 1.0, 4.0},
            {320.0, 192.0, 64.0, -80.0, 40.0, -48.0, -6.0, -11.0, 0.0, -3.0},
            {640.0, 384.0, 128.0, -160.0, 80.0, -96.0, -12.0, -22.0, -5.0, 2.0},
            {-3.0, -3.0, 0.0, 0.0, 5.0, 3.0, -2.0, 2.0, 5.0, -2.0},

    };

    private static double[][] TASK_B = new double[][]{
            {-18},
            {2},
            {-8},
            {-6},
            {24},
            {30},
            {64},
            {100},
            {216},
            {0},
    };

    private static double[][] PYTHON_ANSWER = new double[][]{
            {5.34988720e-15},
            {2.00000000e+00},
            {-4.21884749e-14},
            {2.00000000e+00},
            {2.71310752e-15},
            {2.00000000e+00},
            {8.11850587e-16},
            {2.00000000e+00},
            {1.34441069e-15},
            {2.00000000e+00},
    };
//endregion

    public static void main(String[] args) {
        for(int k=1; k<11;k++){
            Matrix a = cropColumns(TASK_A,k);
            Matrix ta = Matrix.transposeMatrix(a);
            Matrix b = new Matrix(TASK_B);

            var newA = Matrix.multiplyMatrix(ta, a);
            var newB = Matrix.multiplyMatrix(ta, b);

            Matrix aDecomposed = Matrix.choleskyDecomposition(newA);
            //System.out.println(aDecomposed);
            var ans = Matrix.solveCholesky(aDecomposed, newB);


          // System.out.println("Ans:");
          //System.out.println(Arrays.toString(ans));


            var customInfelicity = new double[a.getI()];
            for (int i = 0; i < a.getI(); i++) {
                double sum = 0;
                for (int j = 0; j < a.getJ(); j++) {
                    sum += a.matrix[i][j] * ans[j];
                }
                customInfelicity[i] = sum;
            }

            var inf=Matrix.countInfelicity(customInfelicity, convertMatrixToVector(b));
            System.out.println("Residual rate "+ inf+" k= "+k);

            if(a.getJ()==10){
                System.out.println("\n********************************************************************************");
                inf=Matrix.countInfelicity(customInfelicity, convertMatrixToVector(b));
                System.out.println("Infelicity of custom count "+ inf);
                var pyMatrix = new Matrix(PYTHON_ANSWER);
                inf= Matrix.countInfelicity(convertMatrixToVector(Matrix.multiplyMatrix(a, pyMatrix)),convertMatrixToVector(b));
                System.out.println( "Infelicity of python function count "+ inf);
                System.out.println("********************************************************************************\n");
            }
        }
    }

    private static double[] convertMatrixToVector(Matrix a) {
        var ans=new double[a.getI()];
        for (int i = 0; i < a.getI(); i++) {
            ans[i]=a.matrix[i][0];
        }
        return ans;
    }

    private static Matrix cropColumns(Matrix a, int k){
        var ans=new double[a.getI()][k];
        for(int j=0;j<k;j++){
            for(int i=0;i<a.getI();i++){
                ans[i][j]=a.matrix[i][j];
            }
        }
        return new Matrix(ans);
    }

    private static Matrix cropColumns(double[][] a, int k){
        var ans=new double[a.length][k];
        for(int j=0;j<k;j++){
            for(int i=0;i<a.length;i++){
                ans[i][j]=a[i][j];
            }
        }
        return new Matrix(ans);
    }
}
