# Spring Cache Abstraction with Redis

## Enable Caching

```java
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
        var config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new Jackson2JsonRedisSerializer<>(Object.class)));

        return RedisCacheManager.builder(factory)
            .cacheDefaults(config)
            .build();
    }
}
```

## Usage on Service Methods

```java
@Service
public class EmployeeService {

    @Cacheable(value = "employees", key = "#id")
    public EmployeeDTO findById(Long id) { ... }

    @CacheEvict(value = "employees", key = "#dto.empId")
    public EmployeeDTO save(EmployeeDTO dto) { ... }

    @CacheEvict(value = "employees", allEntries = true)
    public void clearCache() { }
}
```

## Rules

- Add `@EnableCaching` to a `@Configuration` class — not to `@SpringBootApplication`.
- Always set a default TTL on `RedisCacheConfiguration` to prevent unbounded cache growth.
- Use `@CacheEvict` on mutation methods to keep cache consistent.
- Cache names match the `value` attribute — keep them short and meaningful.
