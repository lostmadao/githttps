package com;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ScannerTest {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入数字");
//        int i = sc.nextInt(2);
//        int i = sc.nextInt();
        Random r = new Random();
        int i = r.nextInt(100);
        System.out.println(i);
    }

    @Test
    public void fun(){
//        int[] arr={3,1,4,9,8};
//        Arrays.sort(arr);
//        int index = Arrays.binarySearch(arr, 4);
//        System.out.println(index);
//        System.out.println(arr[index]);
//        int i = Math.abs(-10);
//        double i = Math.ceil(-12.5); //返回大于等于参数的最小整数
//        double i = Math.floor(-12.5); //返回小于等于参数的最大值
//        long i = Math.round(-12.6);  //四舍五入
        double i = Math.random();  //返回0.0~1之间的数
        System.out.println(i);
    }
}
