# 部署指南

本文档详细说明如何部署StellarCRM系统到生产环境。

## 目录
1. [系统要求](#系统要求)
2. [Docker部署（推荐）](#docker部署推荐)
3. [手动部署](#手动部署)
4. [环境变量配置](#环境变量配置)
5. [数据库初始化](#数据库初始化)
6. [反向代理配置](#反向代理配置)
7. [监控与日志](#监控与日志)
8. [故障排除](#故障排除)

## 系统要求

### 硬件要求
- CPU: 4核或以上
- 内存: 8GB或以上
- 存储: 50GB可用空间（根据数据量调整）
- 网络: 稳定的互联网连接

### 软件要求
- Docker 20.10+
- Docker Compose 1.29+
- Linux/Unix系统（推荐Ubuntu 20.04+）
- Git

## Docker部署（推荐）

### 1. 克隆项目
```bash
git clone <repository-url>
cd stellar-crm
```

### 2. 构建并启动服务
```bash
# 构建并启动所有服务
docker-compose up -d --build

# 查看服务状态
docker-compose ps

# 查看日志
docker-compose logs -f
```

### 3. 访问应用
- 前端界面: http://your-server-ip
- 后端API: http://your-server-ip:8080
- API文档: http://your-server-ip:8080/swagger-ui.html

### 4. 停止服务
```bash
# 停止所有服务
docker-compose down

# 停止并删除卷（注意：这将删除所有数据）
docker-compose down -v
```

## 手动部署

### 后端部署

#### 1. 环境准备
确保服务器上已安装以下软件：
- Java 17+
- Maven 3.8+
- MySQL 8.0+
- Neo4j 5.x+
- Redis 7.x+

#### 2. 数据库配置
```bash
# 创建数据库
mysql -u root -p -e "CREATE DATABASE stellar_crm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# 创建用户并授权
mysql -u root -p -e "CREATE USER 'stellar_crm'@'%' IDENTIFIED BY 'secure_password';"
mysql -u root -p -e "GRANT ALL PRIVILEGES ON stellar_crm.* TO 'stellar_crm'@'%';"
mysql -u root -p -e "FLUSH PRIVILEGES;"
```

#### 3. 应用打包
```bash
cd auto-crm-backend

# 编译项目
mvn clean package -DskipTests

# 运行应用
java -jar target/auto-crm-backend-1.0.0.jar
```

#### 4. 后台运行
```bash
# 使用nohup后台运行
nohup java -jar target/auto-crm-backend-1.0.0.jar > backend.log 2>&1 &

# 或使用systemd服务（推荐）
sudo cp deployment/stellar-crm-backend.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable stellar-crm-backend
sudo systemctl start stellar-crm-backend
```

### 前端部署

#### 1. 环境准备
确保服务器上已安装以下软件：
- Node.js 20.19.5+
- Nginx

#### 2. 构建项目
```bash
cd auto-crm-frontend

# 安装依赖
npm ci --only=production

# 构建生产版本
npm run build-only
```

#### 3. 部署到Nginx
```bash
# 复制构建产物到Nginx目录
sudo cp -r dist/* /var/www/stellar-crm/

# 配置Nginx（参考下方配置示例）
sudo cp deployment/nginx.conf /etc/nginx/sites-available/stellar-crm
sudo ln -s /etc/nginx/sites-available/stellar-crm /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

## 环境变量配置

### 后端环境变量
在生产环境中，可以通过以下方式配置环境变量：

#### 1. application-prod.properties
```properties
# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/stellar_crm?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=stellar_crm
spring.datasource.password=secure_password

# Neo4j配置
spring.neo4j.uri=bolt://localhost:7687
spring.neo4j.authentication.username=neo4j
spring.neo4j.authentication.password=neo4j_password

# Redis配置
spring.redis.host=localhost
spring.redis.port=6379

# JWT配置
jwt.secret=your-very-secure-secret-key-here-at-least-256-bits
jwt.expiration=86400000

# AI服务配置
ai.service.url=https://api.your-ai-service.com
ai.service.api-key=your-ai-service-api-key
```

#### 2. 系统环境变量
```bash
export SPRING_PROFILES_ACTIVE=prod
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/stellar_crm
export SPRING_DATASOURCE_USERNAME=stellar_crm
export SPRING_DATASOURCE_PASSWORD=secure_password
```

### 前端环境变量
前端环境变量在构建时确定，可以在 `.env.production` 文件中配置：

```bash
# API基础URL
VITE_API_BASE_URL=https://your-domain.com/api

# 应用名称
VITE_APP_NAME=StellarCRM

# 版本号
VITE_APP_VERSION=1.0.0
```

## 数据库初始化

### 自动初始化
系统首次启动时会自动创建数据库表结构并插入初始数据。

### 手动初始化
如果需要手动初始化数据库，可以执行以下脚本：

```bash
# 执行数据库初始化脚本
mysql -u stellar_crm -p stellar_crm < auto-crm-backend/src/main/resources/db/init.sql

# 插入初始数据
mysql -u stellar_crm -p stellar_crm < auto-crm-backend/src/main/resources/db/data.sql
```

## 反向代理配置

### Nginx配置示例
```nginx
server {
    listen 80;
    server_name your-domain.com;
    
    # 重定向到HTTPS
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name your-domain.com;
    
    # SSL证书配置
    ssl_certificate /path/to/your/certificate.crt;
    ssl_certificate_key /path/to/your/private.key;
    
    # 安全头配置
    add_header X-Frame-Options "SAMEORIGIN" always;
    add_header X-XSS-Protection "1; mode=block" always;
    add_header X-Content-Type-Options "nosniff" always;
    add_header Referrer-Policy "no-referrer-when-downgrade" always;
    add_header Content-Security-Policy "default-src 'self' http: https: data: blob: 'unsafe-inline'" always;
    
    # 静态文件缓存
    location ~* \.(jpg|jpeg|png|gif|ico|css|js)$ {
        root /var/www/stellar-crm;
        expires 1y;
        add_header Cache-Control "public, immutable";
    }
    
    # 前端应用
    location / {
        root /var/www/stellar-crm;
        try_files $uri $uri/ /index.html;
    }
    
    # API代理
    location /api/ {
        proxy_pass http://localhost:8080/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        
        # 超时配置
        proxy_connect_timeout 60s;
        proxy_send_timeout 60s;
        proxy_read_timeout 60s;
    }
    
    # WebSocket支持
    location /ws/ {
        proxy_pass http://localhost:8080/ws/;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
}
```

## 监控与日志

### 日志配置

#### 后端日志
后端使用Logback进行日志记录，默认配置如下：

```xml
<!-- logback-spring.xml -->
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/application.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/application.%d{yyyy-MM-dd}.%i.gz</fileNamePattern>
            <maxFileSize>100MB</maxFileSize>
            <maxHistory>30</maxHistory>
            <totalSizeCap>3GB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="FILE" />
    </root>
</configuration>
```

#### 前端日志
前端使用console进行日志记录，在生产环境中可以通过以下方式收集：

```javascript
// 在生产环境中启用错误收集
if (process.env.NODE_ENV === 'production') {
  // 全局错误处理
  window.addEventListener('error', (event) => {
    // 发送错误信息到日志服务
    fetch('/api/logs/error', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        message: event.message,
        filename: event.filename,
        lineno: event.lineno,
        colno: event.colno,
        error: event.error?.stack,
        timestamp: new Date().toISOString(),
      }),
    });
  });
}
```

### 性能监控

#### Prometheus配置
后端集成了Micrometer，可以与Prometheus集成：

```yaml
# prometheus.yml
scrape_configs:
  - job_name: 'stellar-crm'
    static_configs:
      - targets: ['your-server-ip:8080']
```

#### Grafana仪表板
导入提供的Grafana仪表板JSON文件来监控应用性能。

## 故障排除

### 常见问题

#### 1. 数据库连接失败
```bash
# 检查数据库服务状态
sudo systemctl status mysql
sudo systemctl status neo4j
sudo systemctl status redis

# 检查网络连接
telnet your-db-host 3306
telnet your-neo4j-host 7687
telnet your-redis-host 6379
```

#### 2. Docker容器无法启动
```bash
# 查看容器日志
docker-compose logs <service-name>

# 检查容器状态
docker-compose ps

# 重建容器
docker-compose up -d --force-recreate --no-deps <service-name>
```

#### 3. 前端页面空白
```bash
# 检查Nginx配置
sudo nginx -t

# 查看Nginx错误日志
sudo tail -f /var/log/nginx/error.log

# 检查前端构建文件
ls -la /var/www/stellar-crm/
```

#### 4. API请求失败
```bash
# 检查后端服务状态
curl -v http://localhost:8080/actuator/health

# 查看后端日志
tail -f logs/application.log

# 检查防火墙设置
sudo ufw status
```

### 紧急恢复

#### 1. 数据库备份恢复
```bash
# 备份数据库
mysqldump -u stellar_crm -p stellar_crm > backup-$(date +%Y%m%d-%H%M%S).sql

# 恢复数据库
mysql -u stellar_crm -p stellar_crm < backup-file.sql
```

#### 2. 应用回滚
```bash
# Docker部署回滚
docker-compose down
git checkout <previous-stable-commit>
docker-compose up -d --build

# 手动部署回滚
sudo systemctl stop stellar-crm-backend
# 替换为旧版本jar文件
sudo systemctl start stellar-crm-backend
```

## 安全建议

1. **定期更新**: 保持系统和依赖库更新到最新稳定版本
2. **访问控制**: 限制对服务器的SSH访问，使用密钥认证
3. **防火墙**: 配置防火墙只开放必要端口
4. **备份策略**: 定期备份数据库和重要文件
5. **监控告警**: 设置系统监控和异常告警
6. **日志审计**: 定期审查系统日志，检测异常行为

## 性能优化

1. **数据库优化**: 添加适当索引，优化慢查询
2. **缓存策略**: 合理使用Redis缓存热点数据
3. **CDN加速**: 对静态资源使用CDN分发
4. **负载均衡**: 在高流量场景下使用负载均衡
5. **资源压缩**: 启用Gzip压缩减少传输大小
6. **图片优化**: 使用WebP格式和适当的图片尺寸

通过遵循本部署指南，您可以成功地将StellarCRM系统部署到生产环境，并确保其稳定、安全地运行。