package com.moon.netty.test.b_java_nio;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: demo
 * @description: 从本地文件里读取
 * @author: 小月
 * @create: 2020-03-29 11:57
 **/

/**
 * 应用实例:从本地文件中读取
 * 1.使用前面学习后的ByteBuffer(缓冲) 和 FileChannel(通道)， 将 file01.txt 中的数据读入到程序，并显示在控制台屏幕
 * 2.假定文件已经存在
 */
@Slf4j
public class C_NIOFileChannel02 {
    public static void main(String[] args) throws Exception {
        //创建文件的输入流
        File file = new File("D:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过fileInputStream 获取对应的FileChannel -> 实际类型  FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将 通道的数据读入到Buffer
        fileChannel.read(byteBuffer);

        //将byteBuffer 的 字节数据 转成String
        log.info(new String(byteBuffer.array()));
        fileInputStream.close();



    }


}
