package com.moon.demo.java8.stream;

import com.moon.demo.golf23.d_proto.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述:
 * 创建人: 小月
 * 创建时间: 2020-08-04 23:42
 */
public class TestStream {

    public static void main(String[] args) {
        test12();
    }

    /**
     * Stream的构造
     */
    public static void test1() {
        Stream stream = Stream.of("a", "b", "c", 23);
        stream.forEach(key -> System.out.println(key));

        String[] array = new String[]{"abc", "efg"};
        stream = Stream.of(array);
        stream = Arrays.stream(array);
        stream.forEach(key -> System.out.println(key));

        List<String> list = Arrays.asList(array);
        stream = list.stream();

        //IntStream、LongStream、DoubleStream
        IntStream stream2 = IntStream.of(1, 2, 3, 3);
        DoubleStream stream4 = DoubleStream.of(1, 2, 3, 3.4);

        stream2.forEach(key -> System.out.println(key));
        stream4.forEach(key -> System.out.println(key));
    }

    /**
     * Stream的转换
     */
    public static void test2() {
        Stream stream = Stream.of("abc", "def");

        String[] array = (String[]) stream.toArray(String[]::new);
        System.out.println(array.length);//2
        List<String> list = (List<String>) Stream.of("1", "2", "3").collect(Collectors.toList());
        String str = Stream.of("abc", "mn").collect(Collectors.joining()).toString();
        System.out.println(array);//[Ljava.lang.String;@35851384  ---对象
        System.out.println(list);//[1, 2, 3]
        System.out.println(str);//abcmn
    }

    /**
     * 一个 Stream 只可以使用一次
     * stream has already been operated upon or closed
     */
    public static void test3() {
        Stream stream = Stream.of(1, 2, 3, 2);
        System.out.println("count:" + stream.count());
        System.out.println("count:" + stream.count());
    }

    /**
     * 转换大写
     * jdk8中使用了::的用法。就是把方法当做参数传到stream内部，
     * 使stream的每个元素都传入到该方法里面执行一下，双冒号运算就是Java中的[方法引用],[方法引用]的格式是:
     * 类名：：方法名
     * 案例:
     * 表达式:
     * person -> person.getAge();
     * 使用双冒号:
     * Person::getAge
     * 表达式:
     * new HashMap<>()
     * 使用双冒号:
     * HsahMap::new
     */
    public static void test4() {
        List<String> list = Arrays.asList("a", "MnM");

        List<String> result = list.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        System.out.println(list);
        System.out.println(result);
    }

    /**
     * 平方
     */
    public static void test5() {
        List<Integer> list2 = Arrays.asList(1, 2, 4);
        List<Integer> list3 = list2.stream().
                map(key -> key * key).
                collect(Collectors.toList());
        System.out.println(list2);
        System.out.println(list3);
    }

    /**
     * 找偶数
     */
    public static void test6() {
        List<Integer> list2 = Arrays.asList(1, 2, 4);
        List<Integer> list3 = list2.stream().
                filter(key -> key % 2 == 0).
                collect(Collectors.toList());
        System.out.println(list2);//[1, 2, 4]
        System.out.println(list3);//[2, 4]
    }

    /**
     * 区间值
     */
    public static void test7() {
        System.out.println("\n");
        IntStream.range(1, 3).forEach(System.out::println);
        System.out.println("\n");
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    /**
     * 并发
     */
    public static void test8() {
        IntStream.rangeClosed(1, 10).parallel().forEach(System.out::println);
    }


    /**
     * 新的Stream继续操作
     * map,peek,foreach的区别:
     * map:应用一个函数型的接口，返回一个新流，是一个中间操作
     * peek:接收一个消费型的接口，是一个中间操作，主要是用于debug的，可以进行二次的流处理
     * foreach:接收一个消费型的接口，然后无返回值，是一个终止操作，注意线程安全问题及集合遍历的顺序问题
     */
    public static void test9() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    /**
     * Optional
     */
    public static void print(String text) {
        System.out.println("<<<<<<");
        System.out.println(Optional.ofNullable(text));
        List<String> obj = new ArrayList<>();
        Optional.ofNullable(text).ifPresent(System.out::println);
        System.out.println(">>>>>>>>>>>>\n");
    }

    public static int getLength(String text) {
        return Optional.ofNullable(text).map(String::length).orElse(-1);
    }

    public static void test10() {
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);

        System.out.println(getLength(strA));
        System.out.println(getLength(""));
        System.out.println(getLength(strB));
    }

    /**
     * 字符串拼接、最值、求和、过滤
     */
    public static void test11() {
        String concat = Stream.of("A", "B", "C").reduce("", String::concat);
        System.out.println("concat:" + concat);

        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("min:" + minValue);

        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println("sum1:" + sumValue);

        int sumValue2 = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("sum2:" + sumValue2);

        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
        System.out.println("concat:" + concat);
    }

    /**
     * limit, skip
     */
    public static void test12() {
        List<Person> persons = new ArrayList<>();
        IntStream.range(1, 1000).forEach(key -> persons.add(new Person("jihite:")));
        List<String> personList = persons.stream().map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
        System.out.println(personList);
    }

    /**
     * 找出最长一行的长度
     */
    public static void test13() throws IOException {
        String path = "**/Person.java";
        BufferedReader br = new BufferedReader(new FileReader(path));
        int longest = br.lines()
                .mapToInt(String::length)
                .max()
                .getAsInt();
        br.close();
        System.out.println(longest);
    }

    /**
     * 找出全文的单词，转小写，并排序
     */
    public void test14() throws IOException {
        String path = "**/Person.java";
        BufferedReader br = new BufferedReader(new FileReader(path));
        List<String> words = br.lines()
                .flatMap(line -> Stream.of(line.split(" ")))
                .filter(word -> word.length() > 0)
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        br.close();
        System.out.println(words);
        words.forEach(key -> System.out.println(key));
    }

}
