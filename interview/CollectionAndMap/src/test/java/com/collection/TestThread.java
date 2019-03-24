package com.collection;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestThread {

    public static void main(String[] args) {
       /* new MyThread1().start();
        Mythread2 tr = new Mythread2();
        new Thread(tr).start();*/

//       匿名内部类
       /* new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println("要看的真多"+i);
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println("阿伟大型阵亡现场....."+i);
                }
            }
        }).start();*/

//       callable方式实现线程

        /*
         * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
         *
         * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
         * FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
         */

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                for (int i = 0; i < 50; i++) {
                    System.out.println("啊！是黑丝攻击，我死了"+i);
                }
                return "伊斯坦布尔";
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(callable); //FutureTask为runnable接口的子类
        new Thread(futureTask).run();

        try {
            System.out.println(futureTask.get()); //运行结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    static class MyThread1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println("thread01....."+i);
            }
        }
    }

    static class Mythread2 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println("thread2........"+i);
            }
        }
    }

}
