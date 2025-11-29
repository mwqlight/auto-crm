# 性能优化与安全加固指南

本文档详细说明StellarCRM系统的性能优化策略和安全加固措施。

## 目录
1. [性能优化](#性能优化)
   - [后端优化](#后端优化)
   - [前端优化](#前端优化)
   - [数据库优化](#数据库优化)
   - [缓存策略](#缓存策略)
2. [安全加固](#安全加固)
   - [认证与授权](#认证与授权)
   - [数据安全](#数据安全)
   - [网络安全](#网络安全)
   - [应用安全](#应用安全)
3. [监控与告警](#监控与告警)
4. [最佳实践](#最佳实践)

## 性能优化

### 后端优化

#### 1. 代码层面优化
```java
// 使用异步处理耗时操作
@Async
public CompletableFuture<Void> processLargeData(List<Data> dataList) {
    // 处理大量数据
    return CompletableFuture.completedFuture(null);
}

// 使用缓存减少数据库查询
@Cacheable(value = "customers", key = "#id")
public Customer getCustomer(Long id) {
    return customerRepository.findById(id);
}

// 批量处理数据
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Modifying
    @Query("UPDATE Customer c SET c.status = :status WHERE c.id IN :ids")
    void batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") String status);
}
```

#### 2. JVM优化
```bash
# JVM启动参数优化
java -Xms2g -Xmx4g -XX:NewRatio=1 -XX:+UseG1GC -XX:MaxGCPauseMillis=200 \
     -XX:+UnlockExperimentalVMOptions -XX:+UseStringDeduplication \
     -jar auto-crm-backend-1.0.0.jar
```

#### 3. 连接池优化
```yaml
# application.yml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
      leak-detection-threshold: 60000
```

### 前端优化

#### 1. 代码分割与懒加载
```javascript
// 路由懒加载
const CustomerView = () => import('@/views/customer/CustomerView.vue')
const SalesView = () => import('@/views/sales/SalesView.vue')

// 组件懒加载
const AsyncComponent = defineAsyncComponent(() =>
  import('@/components/HeavyComponent.vue')
)
```

#### 2. 虚拟滚动处理大数据列表
```vue
<template>
  <RecycleScroller
    class="scroller"
    :items="customers"
    :item-size="54"
    key-field="id"
    v-slot="{ item }"
  >
    <CustomerItem :customer="item" />
  </RecycleScroller>
</template>

<script setup>
import { RecycleScroller } from 'vue-virtual-scroller'
import 'vue-virtual-scroller/dist/vue-virtual-scroller.css'
</script>
```

#### 3. 图片优化
```vue
<template>
  <img 
    v-lazy="customer.avatar" 
    :alt="customer.name"
    loading="lazy"
  />
</template>

<script setup>
// 使用vue3-lazyload插件
import { Lazyload } from 'vue3-lazyload'

app.use(Lazyload, {
  loading: '/images/loading.gif',
  error: '/images/error.png'
})
</script>
```

### 数据库优化

#### 1. 索引优化
```sql
-- 复合索引优化查询
CREATE INDEX idx_customer_name_industry ON customers(name, industry);

-- 覆盖索引减少回表查询
CREATE INDEX idx_opportunity_customer_status ON opportunities(customer_id, status, value);

-- 函数索引优化日期查询
CREATE INDEX idx_opportunity_close_date ON opportunities(DATE(expected_close_date));
```

#### 2. 查询优化
```java
// 使用投影查询只获取需要的字段
@Query("SELECT new com.stellar.crm.dto.CustomerSummary(c.id, c.name, c.industry) FROM Customer c WHERE c.industry = :industry")
List<CustomerSummary> findCustomerSummaryByIndustry(@Param("industry") String industry);

// 使用分页避免大量数据加载
Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
Page<Customer> customers = customerRepository.findAll(pageable);
```

#### 3. 连接池配置
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/stellar_crm?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true
    hikari:
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
      maximum-pool-size: 20
      minimum-idle: 5
      pool-name: StellarCRM-HikariCP
```

### 缓存策略

#### 1. 多级缓存架构
```java
@Service
public class CustomerService {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    // 一级缓存：Caffeine本地缓存
    private final Cache<String, Customer> localCache = Caffeine.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build();
    
    // 二级缓存：Redis分布式缓存
    public Customer getCustomer(Long id) {
        // 先查本地缓存
        Customer customer = localCache.getIfPresent(id.toString());
        if (customer != null) {
            return customer;
        }
        
        // 再查Redis缓存
        String key = "customer:" + id;
        customer = (Customer) redisTemplate.opsForValue().get(key);
        if (customer != null) {
            localCache.put(id.toString(), customer);
            return customer;
        }
        
        // 最后查数据库
        customer = customerRepository.findById(id);
        if (customer != null) {
            redisTemplate.opsForValue().set(key, customer, 30, TimeUnit.MINUTES);
            localCache.put(id.toString(), customer);
        }
        
        return customer;
    }
}
```

#### 2. 缓存预热
```java
@Component
public class CacheWarmUp {
    
    @Autowired
    private CustomerService customerService;
    
    @EventListener(ApplicationReadyEvent.class)
    public void warmUpCache() {
        // 预热热门客户数据
        List<Long> hotCustomerIds = getHotCustomerIds();
        hotCustomerIds.parallelStream().forEach(customerService::getCustomer);
    }
    
    private List<Long> getHotCustomerIds() {
        // 从数据库或日志分析获取热门客户ID
        return Arrays.asList(1L, 2L, 3L, 4L, 5L);
    }
}
```

## 安全加固

### 认证与授权

#### 1. JWT安全配置
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
```

#### 2. JWT工具类
```java
@Component
public class JwtTokenUtil {
    
    private String secret = "stellar-crm-secret-key-should-be-at-least-256-bits";
    
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10小时
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
```

#### 3. RBAC权限控制
```java
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    
    @PreAuthorize("hasRole('ADMIN') or hasPermission('customer:read')")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        // 获取客户列表
    }
    
    @PreAuthorize("hasRole('ADMIN') or hasPermission('customer:create')")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        // 创建客户
    }
    
    @PreAuthorize("hasRole('ADMIN') or hasPermission('customer:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        // 删除客户
    }
}
```

### 数据安全

#### 1. 敏感数据加密
```java
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Convert(converter = AESEncryptionConverter.class)
    private String phone;
    
    @Convert(converter = AESEncryptionConverter.class)
    private String email;
    
    // getters and setters
}

@Converter
public class AESEncryptionConverter implements AttributeConverter<String, String> {
    
    private static final String SECRET_KEY = "stellar-crm-aes-secret-key-32-chars!!";
    
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        return AESUtil.encrypt(attribute, SECRET_KEY);
    }
    
    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return AESUtil.decrypt(dbData, SECRET_KEY);
    }
}
```

#### 2. 密码安全
```java
@Service
public class UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User createUser(UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        // 使用BCrypt加密密码
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        
        return userRepository.save(user);
    }
}

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用BCryptPasswordEncoder，强度为12
        return new BCryptPasswordEncoder(12);
    }
}
```

### 网络安全

#### 1. HTTPS配置
```yaml
# application-prod.yml
server:
  port: 8443
  ssl:
    key-store: classpath:keystore.p12
    key-store-password: password
    key-store-type: PKCS12
    key-alias: tomcat
```

#### 2. CORS配置
```java
@Configuration
public class CorsConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("https://*.stellarcrm.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

#### 3. 请求限制
```java
@RestController
@RequestMapping("/api/v1")
@RateLimiter(name = "api", fallbackMethod = "rateLimitFallback")
public class PublicController {
    
    @GetMapping("/public/data")
    public ResponseEntity<Data> getPublicData() {
        // 公共接口
    }
    
    public ResponseEntity<String> rateLimitFallback(Exception ex) {
        return ResponseEntity.status(429).body("请求过于频繁，请稍后再试");
    }
}

@Configuration
public class RateLimitConfig {
    
    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
            .limitRefreshPeriod(Duration.ofMinutes(1))
            .limitForPeriod(100)
            .timeoutDuration(Duration.ofSeconds(10))
            .build();
            
        RateLimiterRegistry registry = RateLimiterRegistry.of(config);
        registry.rateLimiter("api");
        return registry;
    }
}
```

### 应用安全

#### 1. 输入验证
```java
@Data
public class CustomerRequest {
    
    @NotBlank(message = "客户名称不能为空")
    @Size(max = 100, message = "客户名称长度不能超过100个字符")
    private String name;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
    
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;
    
    @Size(max = 255, message = "地址长度不能超过255个字符")
    private String address;
}

@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerController {
    
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequest request) {
        // 创建客户
    }
}
```

#### 2. SQL注入防护
```java
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // 使用参数化查询防止SQL注入
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    List<Customer> findByNameContaining(@Param("name") String name);
    
    // 使用JPA Criteria API
    default List<Customer> findByIndustryAndName(String industry, String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);
        
        List<Predicate> predicates = new ArrayList<>();
        if (industry != null) {
            predicates.add(cb.equal(customer.get("industry"), industry));
        }
        if (name != null) {
            predicates.add(cb.like(customer.get("name"), "%" + name + "%"));
        }
        
        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
```

#### 3. XSS防护
```java
@Component
public class XssFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }
}

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value != null) {
            value = cleanXss(value);
        }
        return value;
    }
    
    private String cleanXss(String value) {
        if (value != null) {
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            value = value.replaceAll("'", "&#39;");
            value = value.replaceAll("eval\\((.*)\\)", "");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            value = value.replaceAll("script", "");
        }
        return value;
    }
}
```

## 监控与告警

### 1. 应用监控
```yaml
# application.yml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true
```

### 2. 日志监控
```xml
<!-- logback-spring.xml -->
<configuration>
    <appender name="LOKI" class="com.github.loki4j.logback.Loki4jAppender">
        <http>
            <url>http://loki:3100/loki/api/v1/push</url>
        </http>
        <format>
            <label>
                <pattern>app=stellar-crm,host=${HOSTNAME},level=%level</pattern>
            </label>
            <message>
                <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] %-5level [%thread] %logger{36} - %msg%n</pattern>
            </message>
            <sortByTime>true</sortByTime>
        </format>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="LOKI" />
    </root>
</configuration>
```

### 3. 告警规则
```yaml
# prometheus-alerts.yml
groups:
- name: stellar-crm-alerts
  rules:
  - alert: HighErrorRate
    expr: rate(http_requests_total{status=~"5.."}[5m]) / rate(http_requests_total[5m]) > 0.05
    for: 5m
    labels:
      severity: warning
    annotations:
      summary: "High error rate (> 5%)"
      description: "Error rate is above 5% for more than 5 minutes"
      
  - alert: HighLatency
    expr: histogram_quantile(0.95, rate(http_request_duration_seconds_bucket[5m])) > 2
    for: 5m
    labels:
      severity: warning
    annotations:
      summary: "High latency (> 2s)"
      description: "95th percentile latency is above 2 seconds for more than 5 minutes"
```

## 最佳实践

### 1. 代码质量
```xml
<!-- pom.xml -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.7</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
        <execution>
            <id>jacoco-check</id>
            <goals>
                <goal>check</goal>
            </goals>
            <configuration>
                <rules>
                    <rule>
                        <element>PACKAGE</element>
                        <limits>
                            <limit>
                                <counter>LINE</counter>
                                <value>COVEREDRATIO</value>
                                <minimum>0.80</minimum>
                            </limit>
                        </limits>
                    </rule>
                </rules>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### 2. 安全扫描
```bash
# OWASP依赖检查
mvn org.owasp:dependency-check-maven:check

# SonarQube代码质量检查
mvn sonar:sonar

# Trivy容器镜像扫描
trivy image stellar-crm-backend:latest
```

### 3. 性能测试
```bash
# 使用JMeter进行负载测试
jmeter -n -t stellar-crm-test-plan.jmx -l results.jtl

# 使用k6进行性能测试
k6 run --vus 100 --duration 30s script.js
```

通过实施以上性能优化和安全加固措施，StellarCRM系统能够提供高性能、高安全性的服务，确保用户数据的安全和系统的稳定运行。