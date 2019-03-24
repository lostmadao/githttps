package com.singleton;

public class Test {
    public static void main(String[] args) {
//        HungryTest h1 = HungryTest.getHungryTest();
//        HungryTest h2 = HungryTest.getHungryTest();
//        LazyTest h1 = LazyTest.getLazyTest();
//        LazyTest h2 = LazyTest.getLazyTest();
//        DoubleCheck h1 = DoubleCheck.getDoubleCheck();
//        DoubleCheck h2 = DoubleCheck.getDoubleCheck();

        InnerClass h1 = InnerClass.getInnerClass();
        InnerClass h2 = InnerClass.getInnerClass();

        System.out.println(h1);
        System.out.println(h2);
    }
}
