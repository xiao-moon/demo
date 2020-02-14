package com.moon.demo.golf23.a_singleton.a;

/**
 * @Description:懒汉式
 * @Author：xiaojiaxin
 * @Date：2020-02-11 13:59
 */
public class LHan {

    private static LHan singleton;

    /*私有构造方法*/
    private LHan() {
    }

    /**
     * 懒汉式，顾名思义就是实例在用到的时候才去创建，“比较懒”，
     * 用的时候才去检查有没有实例，如果有则返回，没有则新建。
     * 有线程安全和线程不安全两种写法，区别就是synchronized关键字。
     */
    public static LHan getSingleton() {
        if (singleton == null) {
            singleton = new LHan();
        }
        return singleton;
    }


}
