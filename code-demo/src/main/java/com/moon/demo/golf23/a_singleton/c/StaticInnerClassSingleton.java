package com.moon.demo.golf23.a_singleton.c;

/**
 * @Description:静态内部类
 * @Author：xiaojiaxin
 * @Date：2020-02-11 14:08
 */
public class StaticInnerClassSingleton {

    /*静态内部类*/
    private static class InnerClass {
        private static StaticInnerClassSingleton innerSingleton = new StaticInnerClassSingleton();
    }

    /*私有化构造函数*/
    private StaticInnerClassSingleton(){}


    /*调用静态内部类*/
    public static StaticInnerClassSingleton getSingleton() {
        return InnerClass.innerSingleton;
    }


}
