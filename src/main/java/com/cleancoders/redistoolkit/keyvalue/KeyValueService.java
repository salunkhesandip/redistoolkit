package com.cleancoders.redistoolkit.keyvalue;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;

@Service
public class KeyValueService {

    private final RedisTemplate<String, Object> redisTemplate;

    public KeyValueService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /** Store a value. No expiry — lives until deleted. */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /** Store a value with a TTL (time-to-live). Redis auto-deletes it after ttlSeconds. */
    public void setWithTtl(String key, String value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
    }

    /** Retrieve a value by key. Returns null if the key does not exist. */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /** Delete a key. */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /** Check whether a key exists. */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /** Return remaining TTL in seconds. -1 = no expiry, -2 = key does not exist. */
    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /** List all stored keys (pattern * matches everything). */
    public Set<String> allKeys() {
        return redisTemplate.keys("*");
    }
}
