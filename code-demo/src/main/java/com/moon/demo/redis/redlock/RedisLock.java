package com.moon.demo.redis.redlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-02-16 13:38
 */
@Component
public class RedisLock {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    public static String UNLOCK_LUA ;


    /**
     * 释放锁脚本，原子操作
     */
    static {
        StringBuffer sb = new StringBuffer();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }


    /**
     * 获取分布式锁，锁对象、以及设置过期时间应该为原子操作
     */
    public boolean tryLock(String lockKey, String requestId, long expire, TimeUnit timeUnit) {
        try{
            RedisCallback<Boolean> callback = (connection) -> {
                return connection.set(lockKey.getBytes(Charset.forName("UTF-8")),
                        requestId.getBytes(Charset.forName("UTF-8")),
                        Expiration.seconds(timeUnit.toSeconds(expire)),
                        RedisStringCommands.SetOption.SET_IF_ABSENT);
            };
            return (Boolean)redisTemplate.execute(callback);
        } catch (Exception e) {
            logger.error("redis lock error.", e);
        }
        return false;
    }

    /**
     * 释放锁。传入requestId，只能释放自己线程加的锁
     */

    public boolean releaseLock(String lockKey, String requestId) {
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(UNLOCK_LUA.getBytes(),
                    ReturnType.BOOLEAN ,1,
                    lockKey.getBytes(Charset.forName("UTF-8")),
                    requestId.getBytes(Charset.forName("UTF-8")));
        };
        return (Boolean)redisTemplate.execute(callback);
    }

    /**
     * 获取Redis锁的value值
     */
    public String get(String lockKey) {
        try {
            RedisCallback<String> callback = (connection) -> {
                return new String(connection.get(lockKey.getBytes()), Charset.forName("UTF-8"));
            };
            return (String)redisTemplate.execute(callback);
        } catch (Exception e) {
            logger.error("get redis occurred an exception", e);
        }
        return null;
    }


}
