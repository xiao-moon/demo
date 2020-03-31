package com.moon.netty.test.b_java_nio;

/**
 * @program: demo
 * @description:使用一个Buffer完成文件读取
 * @author: 小月
 * @create: 2020-03-29 12:25
 **/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 要求:使用一个Buffer完成文件读取
 * 使用 FileChannel(通道) 和 方法  read , write，完成文件的拷贝
 * 拷贝一个文本文件 1.txt  , 放在项目下即可
 */
public class D_NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D:\\file01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file02.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) { //循环读取

            //这里有一个重要的操作，一定不要忘了
            /*
             public final Buffer clear() {
                position = 0;
                limit = capacity;
                mark = -1;
                return this;
            }
             */
            byteBuffer.clear(); //清空buffer
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read =" + read);
            if (read == -1) { //表示读完
                break;
            }
            //将buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }


}
