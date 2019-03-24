package com.singleton;

public class LazyTest {
//    懒汉式，
    /*优点：不会提前实例化对象
    * 缺点：可能会有线程安全问题*/
    private LazyTest(){};
    private static LazyTest lazyTest;

    public static LazyTest getLazyTest() {
        synchronized (LazyTest.class) {
            if (lazyTest==null){
                lazyTest= new LazyTest();
            }
        }
        return lazyTest;


    }
}
