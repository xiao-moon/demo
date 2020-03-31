package com.moon.netty.test.b_java_nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @program: demo
 * @description:
 * @author: 小月
 * @create: 2020-03-29 12:44
 **/
@Slf4j
public class F_NIOByteBufferPutGet {
    public static void main(String[] args) {
        //创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('月');
        buffer.putShort((short) 4);

        //取出
        buffer.flip();
        log.info(buffer.getInt()+"");
        log.info(buffer.getLong()+"");
        log.info(buffer.getChar()+"");
        log.info(buffer.getShort()+"");
    }
    
}
