package com.smu;

import java.util.Scanner;

public class test {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print(n+"=");

        for(int k=2;k<=n;k++){
            while(n!=k){
                if(n%k==0){
                    System.out.print(k+"*");
                    n = n/k;
                }else{
                    break;
                }
            }

        }
        System.out.print(n);

    }
}
