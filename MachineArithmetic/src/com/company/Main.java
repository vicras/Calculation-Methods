package com.company;

import static java.lang.Math.pow;

public class Main {

    public static void main(String[] args) {
        for(int i=1;i<100;i+=1){
           System.out.println("pow index "+i);
           System.out.printf("value 1+%63.101f\n",pow(10,-i));
           System.out.printf("custom log1p value %50.101f\n",countLog(pow(10,-i)));
           System.out.printf("default log1p value %49.101f\n",Math.log1p(pow(10,-i)));
           System.out.printf("default log value %51.101f\n",Math.log(1+pow(10,-i)));

           System.out.println("**********************************************************************************");
        }


    }
    public static double countLog(double x){
        double temp=x;
        double ans=temp;

        int i=1;
        while(Math.abs(temp)/i>pow(10,-16)){
            i++;
            temp=temp*x*(-1);
            ans+=(temp/i);
        }
        return ans;
    }
}
