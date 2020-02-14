package com.moon.demo.golf23.b_proto;


import java.io.Serializable;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-15 9:34
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Student implements Cloneable, Serializable {
    /**
     *浅克隆：是指拷贝对象时仅仅拷贝对象本身（包括对象中的八大基本数据类型），而不拷贝对象包含的引用指向的对象。
     */
    private String name;
    private String age;
    private Person person;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", person=" + person +
                '}';
    }



    public Student(String name, String age, Person person) {
        this.name = name;
        this.age = age;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     *浅克隆
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    /**
     *深克隆方式一,必须要在Person类也实现clone方法
     */
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//         Student student = (Student)super.clone();
//         student.setPerson((Person)student.getPerson().clone());
//        return null;
//    }
}
