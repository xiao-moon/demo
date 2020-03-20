package com.moon.demo.redis.redlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-02-16 16:22
 */
@Component
public class TestRedisLock {

    @Autowired
    private RedisLock redisLock;


    public void testLock() {
        String redisKey = "testLock";
        String requestId = "123456";
        boolean result = redisLock.tryLock(redisKey, requestId, 60, TimeUnit.SECONDS);
        System.out.println("lock:" + result);
        System.out.println("release lock:" + redisLock.releaseLock(redisKey, requestId));
    }

}
