# JPA + Redis Dual Config Wiring

## Context

This project configures JPA manually via `JpaConfig` (no `spring.jpa.*` auto-config delegation). Redis is added alongside JPA — both share the application context.

## JpaConfig Overview

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.cleancoders.redistoolkit.repository")
public class JpaConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(ds);
        factory.setPackagesToScan("com.cleancoders.redistoolkit.entity");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
```

## Rules

- Do not add `spring.jpa.hibernate.ddl-auto` to `application.yml` without also configuring it in `JpaConfig` — the manual config takes precedence.
- Do not add a second `DataSource` bean; Redis uses its own `RedisConnectionFactory` (not a `DataSource`).
- When adding a new entity, add its package to `setPackagesToScan`.
