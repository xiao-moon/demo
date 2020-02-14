package com.moon.demo.annotation;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-14 23:20
 */

public class Test {

    public static void main(String[] args) throws Exception {

        Class clazz = Person.class;
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(FieldName.class)){
                FieldName annotation = field.getAnnotation(FieldName.class);
                System.out.println(annotation.value());
            }
        }





    }

}
