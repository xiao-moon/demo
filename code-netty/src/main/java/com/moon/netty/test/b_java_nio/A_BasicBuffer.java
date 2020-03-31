package com.moon.netty.test.b_java_nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * @program: demo
 * @description: nio使用的简单案例
 * @author: 小月
 * @create: 2020-03-28 19:25
 **/
@Slf4j
public class A_BasicBuffer {

    /**
     * 常用Buffer子类:
     * ByteBuffer，存储字节数据到缓冲区
     * ShortBuffer，存储字符串数据到缓冲区
     * CharBuffer，存储字符数据到缓冲区
     * IntBuffer，存储整数数据到缓冲区
     * LongBuffer，存储长整型数据到缓冲区
     * DoubleBuffer，存储小数到缓冲区
     * FloatBuffer，存储小数到缓冲区
     * <p>
     * Buffer参数介绍:
     * capacity:容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变
     * limit:表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的
     * position:位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变改值，为下次读写作准备
     * mark:标记
     * <p>
     * Buffer常用方法:
     * //JDK1.4时，引入的api
     * public final int capacity( )//返回此缓冲区的容量
     * public final int position( )//返回此缓冲区的位置
     * public final Buffer position (int newPositio)//设置此缓冲区的位置
     * public final int limit( )//返回此缓冲区的限制
     * public final Buffer limit (int newLimit)//设置此缓冲区的限制
     * public final Buffer mark( )//在此缓冲区的位置设置标记
     * public final Buffer reset( )//将此缓冲区的位置重置为以前标记的位置
     * public final Buffer clear( )//清除此缓冲区, 即将各个标记恢复到初始状态，但是数据并没有真正擦除, 后面操作会覆盖
     * public final Buffer flip( )//反转此缓冲区
     * public final Buffer rewind( )//重绕此缓冲区
     * public final int remaining( )//返回当前位置与限制之间的元素数
     * public final boolean hasRemaining( )//告知在当前位置和限制之间是否有元素
     * public abstract boolean isReadOnly( );//告知此缓冲区是否为只读缓冲区
     * <p>
     * //JDK1.6时引入的api
     * public abstract boolean hasArray();//告知此缓冲区是否具有可访问的底层实现数组
     * public abstract Object array();//返回此缓冲区的底层实现数组
     * public abstract int arrayOffset();//返回此缓冲区的底层实现数组中第一个缓冲区元素的偏移量
     * public abstract boolean isDirect();//告知此缓冲区是否为直接缓冲区
     * <p>
     * ByteBuffer常用方法:
     * //缓冲区创建相关api
     * public static ByteBuffer allocateDirect(int capacity)//创建直接缓冲区
     * public static ByteBuffer allocate(int capacity)//设置缓冲区的初始容量
     * public static ByteBuffer wrap(byte[] array)//把一个数组放到缓冲区中使用
     * //构造初始化位置offset和上界length的缓冲区
     * public static ByteBuffer wrap(byte[] array,int offset, int length)
     * //缓存区存取相关API
     * public abstract byte get( );//从当前位置position上get，get之后，position会自动+1
     * public abstract byte get (int index);//从绝对位置get
     * public abstract ByteBuffer put (byte b);//从当前位置上添加，put之后，position会自动+1
     * public abstract ByteBuffer put (int index, byte b);//从绝对位置上put
     */
    public static void main(String[] args) {
        //创建一个buffer，大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer中存放数据
        //intBuffer.put(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        //如何从buffer读取数据
        //将buffer转换，读写切换
        intBuffer.flip();
        intBuffer.position(1);//从1开始读
        while (intBuffer.hasRemaining()) {
            log.info(intBuffer.get() + "");
        }


    }
}
