package com.moon.demo.golf23.a_singleton.e;

/**
 * @Description:枚举法
 * @Author：xiaojiaxin
 * @Date：2020-02-11 14:25
 */
/*自动支持序列化机制，绝对防止多次实例化*/
public enum EnumSingleton {

    INSTANCE;

    public void anyMethod(){
        //TODO 可以实现任何方法
    }

}
