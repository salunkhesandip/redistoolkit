# RedisTemplate & Serialization

## Bean Configuration

```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        var template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);

        var serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }
}
```

## Common Operations

```java
// String
redisTemplate.opsForValue().set("key", value, Duration.ofMinutes(30));
Object value = redisTemplate.opsForValue().get("key");

// Hash
redisTemplate.opsForHash().put("employee:1", "name", "Alice");
Map<Object, Object> entries = redisTemplate.opsForHash().entries("employee:1");

// List
redisTemplate.opsForList().rightPush("queue:events", event);
Object head = redisTemplate.opsForList().leftPop("queue:events");

// Set
redisTemplate.opsForSet().add("departments", "Engineering", "HR");

// TTL
redisTemplate.expire("key", Duration.ofHours(1));
```

## application.yml

```yaml
spring:
  data:
    redis:
      host: ${REDIS_HOST:localhost}
      port: ${REDIS_PORT:6379}
```

## Rules

- Use `StringRedisSerializer` for keys — keeps Redis keys human-readable.
- Use `Jackson2JsonRedisSerializer` for values — enables JSON inspection in Redis CLI.
- Always set a TTL on cached entries to prevent memory leaks.
- Prefer `opsForValue` for simple key-value; `opsForHash` for structured objects.
