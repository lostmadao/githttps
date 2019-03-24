package com.singleton;


public class InnerClass {
//    静态内部类的方式创建
    private InnerClass(){};

    private static class SingletonClass{
        private static final InnerClass instance = new InnerClass();
    }

    public  static InnerClass getInnerClass(){
        return SingletonClass.instance;
    }
}
