package com.moon.demo.golf23.d_proto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:测试浅克隆，测试不成功
 * @Author：xiaojiaxin
 * @Date：2020-01-15 9:37
 */
public class SimpleClone {

    private static Logger logger = LoggerFactory.getLogger(SimpleClone.class);

    /**
     *浅克隆
     */
    public static void main(String[] args) throws Exception {
        Student student = new Student("小月","18",new Person("刘备"));
        Student clone = (Student)student.clone();
        clone.setName("小紫");
//        clone.setPerson(new Person("曹操"));
        student.setPerson(new Person("曹操"));
        logger.info("对象是否相等:"+(student==clone));
        logger.info("深克隆前对象="+student);
        logger.info("深克隆后对象="+clone);
    }
}
