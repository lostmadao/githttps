package com.singleton;

public class HungryTest {
//    饿汉式，私有构造方法，提供静态共有get访问方法
    private HungryTest(){};
    private static HungryTest hungryTest = new HungryTest();

    public static HungryTest getHungryTest(){
        return hungryTest;
    }
}
