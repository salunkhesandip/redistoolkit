---
name: redis-spring
description: "Redis + Spring Boot skill. Use when configuring RedisTemplate, adding Redis-backed caching or data structures, wiring JPA alongside Redis, or writing Spring Boot tests for Redis-aware components in this project."
argument-hint: "Describe the task (e.g., 'add Redis cache', 'use RedisTemplate for hash operations', 'configure connection factory')"
---

# Redis Spring Boot Development

## When to Use
- Configuring `RedisTemplate` or `StringRedisTemplate` beans
- Adding Redis-backed caching (`@Cacheable`, `@CacheEvict`) or custom data structures
- Wiring manual JPA + Redis configuration (this project uses `JpaConfig` directly)
- Writing tests for Redis-aware components

## Procedure

1. Check `JpaConfig` before adding datasource beans — manual config is already present
2. Load the relevant reference file below
3. Use `RedisTemplate<String, Object>` with `Jackson2JsonRedisSerializer` for objects
4. Prefer Spring Cache abstraction (`@Cacheable`) over direct template calls unless fine-grained control is needed

## References

| Domain | Reference |
|--------|-----------|
| RedisTemplate & serialization | [redis-template.md](./references/redis-template.md) |
| JPA + Redis dual-config wiring | [jpa-config.md](./references/jpa-config.md) |
| Caching with Spring Cache | [caching.md](./references/caching.md) |
