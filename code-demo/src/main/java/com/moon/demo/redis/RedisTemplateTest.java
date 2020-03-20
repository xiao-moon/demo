package com.moon.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description:RedisTemplate操作redis
 * @Author：xiaojiaxin
 * @Date：2020-02-14 20:44
 */
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * Redis的String数据结构
     */
    public void keyValue() {
        // 新增一个字符串类型的值，key是键，value是值。
        redisTemplate.opsForValue().set("num", "123");

        // 设置的是10秒失效，十秒之内查询有结果，十秒之后返回为null
        redisTemplate.opsForValue().set("num", "123", 10, TimeUnit.SECONDS);

        // 覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始
        redisTemplate.opsForValue().set("key", "hello world");
        redisTemplate.opsForValue().set("key", "redis", 6);//结果为:hello redis

        // key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value。
        redisTemplate.opsForValue().setBit("key", 1, false);

        // 获取key键对应的值。
        redisTemplate.opsForValue().get("num");

        // 截取key键对应值得字符串，从开始下标位置开始到结束下标的位置(包含结束下标)的字符串。
        redisTemplate.opsForValue().get("key", 0, 3);

        // 设置键的字符串值并返回其旧值
        redisTemplate.opsForValue().getAndSet("key", "ccc");

        // 判断指定的位置ASCII码的bit位是否为1。
        redisTemplate.opsForValue().getBit("key", 1);

        // 获取指定字符串的长度
        Long stringValueLength = redisTemplate.opsForValue().size("key");

        // 以增量的方式将double值存储在变量中。
        double stringValueDouble = redisTemplate.opsForValue().increment("doubleKey", 5);

        // 以增量的方式将long值存储在变量中。
        long stringValueLong = redisTemplate.opsForValue().increment("longKey", 6);

        // 如果键不存在则新增,存在则不改变已经有的值。
        boolean absentBoolean = redisTemplate.opsForValue().setIfAbsent("absentKey", "fff");

        // 在原有的值基础上新增字符串到末尾。
        // 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。
        // 如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
        redisTemplate.opsForValue().append("test", "Hello");

        // 返回key所对应的value值得长度
        redisTemplate.opsForValue().size("key");

        // 设置map集合到redis。
        Map valueMap = new HashMap();
        valueMap.put("valueMap1", "map1");
        valueMap.put("valueMap2", "map2");
        valueMap.put("valueMap3", "map3");
        redisTemplate.opsForValue().multiSet(valueMap);

        // 根据集合取出对应的value值。
        List paraList = new ArrayList();
        paraList.add("valueMap1");
        paraList.add("valueMap2");
        paraList.add("valueMap3");
        List<String> valueList = redisTemplate.opsForValue().multiGet(paraList);
        for (String value : valueList) {
            System.out.println("通过multiGet(Collection<K> keys)方法获取map值:" + value);
        }

        // 如果对应的map集合名称不存在，则添加，如果存在则不做修改。
        Map valueMap1 = new HashMap();
        valueMap.put("valueMap1", "map1");
        valueMap.put("valueMap2", "map2");
        valueMap.put("valueMap3", "map3");
        redisTemplate.opsForValue().multiSetIfAbsent(valueMap);

    }


    /**
     * Redis的Hash数据机构
     */
    public void hash() {
        // 新增hashMap值。
        redisTemplate.opsForHash().put("hashValue", "map1", "map1-1");

        // 获取指定变量中的hashMap所有value的值。
        redisTemplate.opsForHash().values("hashValue");

        // 获取变量中的键值对。
        redisTemplate.opsForHash().entries("hashValue");

        // 获取变量中的指定map键是否有值,如果存在该map键则获取值，没有则返回null。
        redisTemplate.opsForHash().get("hashValue", "map1");

        // 判断变量中是否有指定的map键。
        redisTemplate.opsForHash().hasKey("hashValue", "map3");

        // 获取变量中的所有键。
        redisTemplate.opsForHash().keys("hashValue");

        // 获取变量的长度。
        redisTemplate.opsForHash().size("hashValue");

        // 使变量中的键以double值的大小进行自增长。
        redisTemplate.opsForHash().increment("hashInc", "map1", 3);

        // 使变量中的键以long值的大小进行自增长。
        redisTemplate.opsForHash().increment("hashInc", "map2", 6);

        // 以集合的方式获取变量中的值。
        List<Object> list = new ArrayList<>();
        list.add("map1");
        list.add("map2");
        List mapValueList = redisTemplate.opsForHash().multiGet("hashValue", list);

        // 以map集合的形式添加键值对。
        Map newMap = new HashMap();
        newMap.put("map3", "map3-3");
        newMap.put("map5", "map5-5");
        redisTemplate.opsForHash().putAll("hashValue", newMap);

        // 如果变量值存在，在变量中可以添加不存在的的键值对，如果变量不存在，则新增一个变量，同时将键值对添加到该变量。
        redisTemplate.opsForHash().putIfAbsent("hashValue", "map6", "map6-6");

        // 匹配获取键值对，ScanOptions.NONE为获取全部键对，ScanOptions.scanOptions().match("map1").build()
        // 匹配获取键位map1的键值对,不能模糊匹配。
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("hashValue", ScanOptions.scanOptions().match("map1").build());
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
        }

        // 删除给定的哈希hashKeys
        redisTemplate.opsForHash().delete("hash", "name", "age");
    }


    /**
     * Redis的List数据结构
     */
    public void list() {

        // 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误。
        redisTemplate.opsForList().size("list");

        // 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
        redisTemplate.opsForList().leftPush("list", "java");
        redisTemplate.opsForList().leftPush("list", "python");
        redisTemplate.opsForList().leftPush("list", "c++");

        // 批量把一个数组插入到列表中
        String[] strs = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().leftPushAll("list", strs);
        redisTemplate.opsForList().range("list", 0, -1);// [3, 2, 1]

        // 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
        redisTemplate.opsForList().rightPush("listRight", "java");
        redisTemplate.opsForList().rightPush("listRight", "python");
        redisTemplate.opsForList().rightPush("listRight", "c++");

        // 批量把一个数组插入到列表中
        String[] strs1 = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list", strs);
        redisTemplate.opsForList().range("list", 0, -1);// [1, 2, 3]

        // 在列表中index的位置设置value值
        redisTemplate.opsForList().set("listRight", 1, "setValue");

        // 从存储在键中的列表中删除等于值的元素的第一个计数事件。
        // count> 0：删除等于从头到尾移动的值的元素。
        // count <0：删除等于从尾到头移动的值的元素。
        // count = 0：删除等于value的所有元素。
        redisTemplate.opsForList().remove("listRight", 1, "setValue");//将删除列表中存储的列表中第一次次出现的“setValue”。

        // 弹出最左边的元素，弹出之后该值在列表中将不复存在
        redisTemplate.opsForList().leftPop("list");

        // 弹出最右边的元素，弹出之后该值在列表中将不复存在
        redisTemplate.opsForList().rightPop("list");
        redisTemplate.opsForList().rightPop("list", 8, TimeUnit.SECONDS);

    }


    /**
     * Redis的Set数据机构
     */
    public void set() {
        // 向变量中批量添加值。
        redisTemplate.opsForSet().add("setValue", "A", "B", "C", "B", "D", "E", "F");

        // 获取变量中的值。
        redisTemplate.opsForSet().members("setValue");

        // 获取变量中值的长度。
        redisTemplate.opsForSet().size("setValue");

        // 随机获取变量中的元素。
        redisTemplate.opsForSet().randomMember("setValue");

        // 随机获取变量中指定个数的元素。
        redisTemplate.opsForSet().randomMembers("setValue", 2);

        // 检查给定的元素是否在变量中。
        redisTemplate.opsForSet().isMember("setValue", "A");

        // 转移变量的元素值到目的变量。
        redisTemplate.opsForSet().move("setValue", "A", "destSetValue");

        // 弹出变量中的元素。弹出之后该值在列表中将不复存在
        redisTemplate.opsForSet().pop("setValue");

        // 批量移除变量中的元素。
        redisTemplate.opsForSet().remove("setValue", "E", "F", "G");

        // 匹配获取键值对，ScanOptions.NONE为获取全部键值对；
        // ScanOptions.scanOptions().match("C").build()匹配获取键位map1的键值对,不能模糊匹配。
        Cursor<Object> cursor = redisTemplate.opsForSet().scan("setValue", ScanOptions.scanOptions().match("C").build());
        while (cursor.hasNext()) {
            Object object = cursor.next();
            System.out.println("通过scan(K key, ScanOptions options)方法获取匹配的值:" + object);
        }

        // 通过集合求差值。
        List list = new ArrayList();
        list.add("destSetValue");
        Set differenceSet = redisTemplate.opsForSet().difference("setValue", list);

        // 通过给定的key求2个set变量的差值。
        redisTemplate.opsForSet().difference("setValue", "destSetValue");

        // 将求出来的差值元素保存。
        redisTemplate.opsForSet().differenceAndStore("setValue", "destSetValue", "storeSetValue");

        // 将求出来的差值元素保存。
        redisTemplate.opsForSet().differenceAndStore("setValue", list, "storeSetValue");

        // 获取去重的随机元素。
        redisTemplate.opsForSet().distinctRandomMembers("setValue", 2);

        // 获取2个变量中的交集。
        redisTemplate.opsForSet().intersect("setValue", "destSetValue");

        // 获取多个变量之间的交集。
        redisTemplate.opsForSet().intersect("setValue", list);

        // 获取2个变量交集后保存到最后一个参数上。
        redisTemplate.opsForSet().intersectAndStore("setValue", "destSetValue", "intersectValue");

        // 获取多个变量的交集并保存到最后一个参数上。
        redisTemplate.opsForSet().intersectAndStore("setValue", list, "intersectListValue");

        // 获取2个变量的合集。
        redisTemplate.opsForSet().union("setValue", "destSetValue");

        // 获取2个变量合集后保存到最后一个参数上。
        redisTemplate.opsForSet().unionAndStore("setValue", "destSetValue", "unionValue");

        // 获取多个变量的合集并保存到最后一个参数上。
        redisTemplate.opsForSet().unionAndStore("setValue", list, "unionListValue");

    }

    public void zSet() {
        // 添加元素到变量中同时指定元素的分值。
        redisTemplate.opsForZSet().add("zSetValue", "A", 1);

        // 获取变量指定区间的元素。
        redisTemplate.opsForZSet().range("zSetValue", 0, -1);

        // 用于获取满足非score的排序取值。这个排序只有在有相同分数的情况下才能使用，如果有不同的分数则返回值不确定。
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lt("D");
        Set zSetValue = redisTemplate.opsForZSet().rangeByLex("zSetValue", range);

        // 用于获取满足非score的设置下标开始的长度排序取值。
        RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        limit.count(2);
        limit.offset(1);//起始下标为0
        zSetValue = redisTemplate.opsForZSet().rangeByLex("zSetValue", range, limit);

        // 通过TypedTuple方式新增数据。
        ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<>("E", 6.0);
        ZSetOperations.TypedTuple<Object> typedTuple2 = new DefaultTypedTuple<Object>("F", 7.0);
        ZSetOperations.TypedTuple<Object> typedTuple3 = new DefaultTypedTuple<Object>("G", 5.0);
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = new HashSet<ZSetOperations.TypedTuple<Object>>();
        typedTupleSet.add(typedTuple1);
        typedTupleSet.add(typedTuple2);
        typedTupleSet.add(typedTuple3);
        redisTemplate.opsForZSet().add("typedTupleSet", typedTupleSet);
        zSetValue = redisTemplate.opsForZSet().range("typedTupleSet", 0, -1);

        // 根据设置的score获取区间值。
        zSetValue = redisTemplate.opsForZSet().rangeByScore("zSetValue", 1, 2);

        // 根据设置的score获取区间值从给定下标和给定长度获取最终值。
        zSetValue = redisTemplate.opsForZSet().rangeByScore("zSetValue", 1, 5, 1, 3);

        //获取RedisZSetCommands.Tuples的区间值。
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet1 = redisTemplate.opsForZSet().rangeWithScores("typedTupleSet", 1, 3);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score = typedTuple.getScore();
            System.out.println("通过rangeWithScores(K key, long start, long end)方法获取RedisZSetCommands.Tuples的区间值:" + value + "---->" + score);
        }

        // 获取RedisZSetCommands.Tuples的区间值通过分值。
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet2 = redisTemplate.opsForZSet().rangeByScoreWithScores("typedTupleSet", 5, 8);
        iterator = typedTupleSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score = typedTuple.getScore();
            System.out.println("通过rangeByScoreWithScores(K key, double min, double max)方法获取RedisZSetCommands.Tuples的区间值通过分值:" + value + "---->" + score);
        }

        // 获取RedisZSetCommands.Tuples的区间值从给定下标和给定长度获取最终值通过分值。
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet3 = redisTemplate.opsForZSet().rangeByScoreWithScores("typedTupleSet", 5, 8, 1, 1);
        iterator = typedTupleSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score = typedTuple.getScore();
            System.out.println("通过rangeByScoreWithScores(K key, double min, double max, long offset, long count)方法获取RedisZSetCommands.Tuples的区间值从给定下标和给定长度获取最终值通过分值:" + value + "---->" + score);
        }

        // 获取区间值的个数。
        long count = redisTemplate.opsForZSet().count("zSetValue", 1, 5);

        // 获取变量中元素的索引,下标开始位置为0。
        long index = redisTemplate.opsForZSet().rank("zSetValue", "B");

        // 匹配获取键值对，ScanOptions.NONE为获取全部键值对；
        // ScanOptions.scanOptions().match("C").build()匹配获取键位map1的键值对,不能模糊匹配。
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("zSetValue", ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = cursor.next();
            System.out.println("通过scan(K key, ScanOptions options)方法获取匹配元素:" + typedTuple.getValue() + "--->" + typedTuple.getScore());
        }

        // 获取元素的分值。
        double score = redisTemplate.opsForZSet().score("zSetValue", "B");

        // 获取变量中元素的个数。
        long zCard = redisTemplate.opsForZSet().zCard("zSetValue");

        // 修改变量中的元素的分值。
        double incrementScore = redisTemplate.opsForZSet().incrementScore("zSetValue", "C", 5);

        // 索引倒序排列指定区间元素。
        redisTemplate.opsForZSet().reverseRange("zSetValue", 0, -1);

        // 倒序排列指定分值区间元素。
        redisTemplate.opsForZSet().reverseRangeByScore("zSetValue", 1, 5);

        // 倒序排列从给定下标和给定长度分值区间元素。
        redisTemplate.opsForZSet().reverseRangeByScore("zSetValue", 1, 5, 1, 2);

        // 倒序排序获取RedisZSetCommands.Tuples的分值区间值。
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet4 = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("zSetValue", 1, 5);
        iterator = typedTupleSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score1 = typedTuple.getScore();
            System.out.println("通过reverseRangeByScoreWithScores(K key, double min, double max)方法倒序排序获取RedisZSetCommands.Tuples的区间值:" + value + "---->" + score1);
        }

        // 倒序排序获取RedisZSetCommands.Tuples的从给定下标和给定长度分值区间值。
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet5 = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("zSetValue", 1, 5, 1, 2);
        iterator = typedTupleSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score1 = typedTuple.getScore();
            System.out.println("通过reverseRangeByScoreWithScores(K key, double min, double max, long offset, long count)方法倒序排序获取RedisZSetCommands.Tuples的从给定下标和给定长度区间值:" + value + "---->" + score1);
        }

        // 索引倒序排列区间值。
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet6 = redisTemplate.opsForZSet().reverseRangeWithScores("zSetValue", 1, 5);
        iterator = typedTupleSet.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            Object value = typedTuple.getValue();
            double score1 = typedTuple.getScore();
            System.out.println("通过reverseRangeWithScores(K key, long start, long end)方法索引倒序排列区间值:" + value + "----->" + score1);
        }

        // 获取倒序排列的索引值。
        long reverseRank = redisTemplate.opsForZSet().reverseRank("zSetValue", "B");

        // 获取2个变量的交集存放到第3个变量里面。
        redisTemplate.opsForZSet().intersectAndStore("zSetValue", "typedTupleSet", "intersectSet");
        zSetValue = redisTemplate.opsForZSet().range("intersectSet", 0, -1);
        System.out.println("通过intersectAndStore(K key, K otherKey, K destKey)方法获取2个变量的交集存放到第3个变量里面:" + zSetValue);

        // 获取2个变量的合集存放到第3个变量里面。
        redisTemplate.opsForZSet().unionAndStore("zSetValue", "typedTupleSet", "unionSet");
        zSetValue = redisTemplate.opsForZSet().range("unionSet", 0, -1);
        System.out.println("通过unionAndStore(K key, K otherKey, K destKey)方法获取2个变量的交集存放到第3个变量里面:" + zSetValue);

        // 获取多个变量的合集存放到第3个变量里面。
        List list = new ArrayList();
        redisTemplate.opsForZSet().unionAndStore("zSetValue", list, "unionListSet");
        zSetValue = redisTemplate.opsForZSet().range("unionListSet", 0, -1);
        System.out.println("通过unionAndStore(K key, Collection<K> otherKeys, K destKey)方法获取多个变量的交集存放到第3个变量里面:" + zSetValue);

        // 批量移除元素根据元素值。
        long removeCount = redisTemplate.opsForZSet().remove("unionListSet", "A", "B");

        // 根据分值移除区间元素。
        removeCount = redisTemplate.opsForZSet().removeRangeByScore("unionListSet", 3, 5);

        // 根据索引值移除区间元素。
        removeCount = redisTemplate.opsForZSet().removeRange("unionListSet", 3, 5);


    }


    /**
     * 其他操作
     */
    public void otherOps() {
        //向redis里存入数据和设置缓存时间  
        redisTemplate.opsForValue().set("baike", "100", 60 * 10, TimeUnit.SECONDS);
        //val做-1操作
        redisTemplate.boundValueOps("baike").increment(-1);
        //根据key获取缓存中的val
        redisTemplate.opsForValue().get("baike");
        //val +1
        redisTemplate.boundValueOps("baike").increment(1);
        //根据key获取过期时间
        redisTemplate.getExpire("baike");
        //根据key获取过期时间并换算成指定单位
        redisTemplate.getExpire("baike", TimeUnit.SECONDS);
        //根据key删除缓存
        redisTemplate.delete("baike");
        //检查key是否存在，返回boolean值
        redisTemplate.hasKey("baike");
        //向指定key中存放set集合
        redisTemplate.opsForSet().add("baike", "1", "2", "3");
        //设置过期时间
        redisTemplate.expire("baike", 1000, TimeUnit.MILLISECONDS);
        //根据key查看集合中是否存在指定数据
        redisTemplate.opsForSet().isMember("baike", "1");
        //根据key获取set集合
        redisTemplate.opsForSet().members("baike");
        //验证有效时间
        Long expire = redisTemplate.boundHashOps("baike").getExpire();
        System.out.println("redis有效时间：" + expire + "S");
        //设置新的key名
        redisTemplate.rename("a","b");

    }


}
