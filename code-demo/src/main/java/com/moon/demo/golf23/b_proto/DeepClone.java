package com.moon.demo.golf23.b_proto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-15 9:53
 */
public class DeepClone {
    private static Logger logger = LoggerFactory.getLogger(DeepClone.class);

    public static void main(String[] args) throws Exception {
        Student student = new Student("小月","18",new Person("刘备"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(student);
        byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Student s = (Student)ois.readObject();
        s.setPerson(new Person("郭嘉"));
        s.setName("小黄");
        logger.info("深克隆前对象="+student);
        logger.info("深克隆后对象="+s);

    }
}
