package com.moon.netty.test.b_java_nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @program: demo
 * @description:
 * @author: 小月
 * @create: 2020-03-29 12:48
 **/
@Slf4j
public class G_ReadOnlyBuffer {
    public static void main(String[] args) {

        //创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for(int i = 0; i < 64; i++) {
            buffer.put((byte)i);
        }

        //读取
        buffer.flip();

        //得到一个只读的Buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        log.info(readOnlyBuffer.getClass()+"");

        //读取
        while (readOnlyBuffer.hasRemaining()) {
            log.info(readOnlyBuffer.get()+"");
        }

        readOnlyBuffer.put((byte)100); //ReadOnlyBufferException
    }


}
