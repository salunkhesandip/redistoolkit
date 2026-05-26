package com.cleancoders.redistoolkit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * Configures RedisTemplate with:
     *   - String keys  → human-readable in redis-cli (e.g. "greeting")
     *   - JSON values  → inspect with redis-cli GET and see plain JSON
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        var template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);

        // Keys are plain strings — easy to read in redis-cli
        var stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // Values are serialized as JSON — works for any Java object
        var jsonSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        return template;
    }
}
