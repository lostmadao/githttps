package com.singleton;

public class DoubleCheck {
//    双重检测锁机制
    private DoubleCheck(){};
    private static DoubleCheck doubleCheck;

    public static DoubleCheck getDoubleCheck() {

        if (doubleCheck==null) {//多线程情况下，两个线程都进入此 if判断
            synchronized (DoubleCheck.class) {
                if (doubleCheck==null) {
                    //这个判断有意义，一个线程执行此段代码之后，另一个线程获取锁对象，
                    // 如果没有此判断，就会创建不同的对象
                    doubleCheck=new DoubleCheck();
                }
            }
        }
        return doubleCheck;
    }
}
