package com.moon.demo.golf23.a_singleton.b;

/**
 * @Description:饿汉式
 * @Author：xiaojiaxin
 * @Date：2020-02-11 14:05
 */
public class EHan {

    private static EHan singleton = new EHan();

    /*私有构造方法*/
    private EHan() {
    }

    /**
     * 饿汉式，从名字上也很好理解，就是“比较勤”，实例在初始化的时候就已经建好了，
     * 不管你有没有用到，都先建好了再说。好处是没有线程安全的问题，坏处是浪费内存空间。
     */
    private static EHan getSingleton() {
        return singleton;
    }

}
