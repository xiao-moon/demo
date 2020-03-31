package com.moon.netty.test.b_java_nio;

/**
 * @program: demo
 * @description:
 * @author: 小月
 * @create: 2020-03-29 12:51
 **/

import lombok.extern.slf4j.Slf4j;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明:
 * 1. MappedByteBuffer 可让文件直接在内存(堆外内存)修改, 操作系统不需要拷贝一次
 */
@Slf4j
public class H_MappedByteBufferTest {
    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\file01.txt", "rw");
        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 参数1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2： 0 ： 可以直接修改的起始位置
         * 参数3:  5: 是映射到内存的大小(不是索引位置) ,即将 1.txt 的多少个字节映射到内存
         * 可以直接修改的范围就是 0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 18);

        mappedByteBuffer.put(0, (byte) 'G');
        mappedByteBuffer.put(3, (byte) 'H');
//        mappedByteBuffer.put(6, (byte) 'H');
//        mappedByteBuffer.putChar(6, (char) '月');
//        mappedByteBuffer.put(18, (byte) 'Y');//IndexOutOfBoundsException

        randomAccessFile.close();
        log.info("修改成功~~");

    }
}
