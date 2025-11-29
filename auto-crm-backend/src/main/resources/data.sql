-- 插入示例客户数据
INSERT INTO customers (id, name, email, phone, company, industry, address, status, tier, lifetime_value, created_at, updated_at) VALUES
(1, '张三', 'zhangsan@example.com', '13800138001', 'ABC科技有限公司', '信息技术', '北京市朝阳区xxx街道xxx号', 'CUSTOMER', 'GOLD', 150000.00, NOW(), NOW()),
(2, '李四', 'lisi@example.com', '13800138002', 'XYZ制造集团', '制造业', '上海市浦东新区xxx路xxx号', 'CUSTOMER', 'PLATINUM', 280000.00, NOW(), NOW()),
(3, '王五', 'wangwu@example.com', '13800138003', 'DEF贸易公司', '贸易', '广州市天河区xxx大道xxx号', 'PROSPECT', 'SILVER', 80000.00, NOW(), NOW()),
(4, '赵六', 'zhaoliu@example.com', '13800138004', 'GHI教育集团', '教育', '深圳市南山区xxx路xxx号', 'LEAD', 'BRONZE', 0.00, NOW(), NOW()),
(5, '钱七', 'qianqi@example.com', '13800138005', 'JKL医疗科技', '医疗健康', '杭州市西湖区xxx街道xxx号', 'CUSTOMER', 'GOLD', 120000.00, NOW(), NOW());

-- 插入示例销售机会数据
INSERT INTO opportunities (id, name, customer_id, value, description, stage, probability, expected_close_date, created_at, updated_at) VALUES
(1, '企业级软件采购项目', 1, 50000.00, '为ABC科技提供定制化企业管理软件', 'NEGOTIATION', 0.8, DATE_ADD(NOW(), INTERVAL 30 DAY), NOW(), NOW()),
(2, '生产线自动化改造', 2, 120000.00, '为XYZ制造提供生产线自动化解决方案', 'PROPOSAL', 0.6, DATE_ADD(NOW(), INTERVAL 45 DAY), NOW(), NOW()),
(3, '供应链管理系统升级', 3, 30000.00, '为DEF贸易升级现有供应链管理系统', 'QUALIFICATION', 0.4, DATE_ADD(NOW(), INTERVAL 60 DAY), NOW(), NOW()),
(4, '在线教育平台开发', 4, 80000.00, '为GHI教育开发新一代在线教育平台', 'PROSPECTING', 0.2, DATE_ADD(NOW(), INTERVAL 90 DAY), NOW(), NOW()),
(5, '医院信息管理系统', 5, 150000.00, '为JKL医疗开发医院信息管理系统', 'CLOSED_WON', 1.0, DATE_SUB(NOW(), INTERVAL 10 DAY), NOW(), NOW());

-- 插入示例服务工单数据
INSERT INTO tickets (id, title, description, customer_id, priority, status, category, assigned_to, created_at, updated_at, resolved_at) VALUES
(1, '软件安装问题', '客户反馈在安装过程中遇到兼容性问题', 1, 'HIGH', 'IN_PROGRESS', 'TECHNICAL', '技术支持组', NOW(), NOW(), NULL),
(2, '功能需求咨询', '客户咨询关于报表功能的详细使用方法', 2, 'MEDIUM', 'OPEN', 'FEATURE', '客户成功组', NOW(), NOW(), NULL),
(3, '账单疑问', '客户对上月账单中的某项费用有疑问', 3, 'MEDIUM', 'OPEN', 'BILLING', '财务组', NOW(), NOW(), NULL),
(4, '系统性能问题', '客户反馈系统在高峰期响应缓慢', 1, 'URGENT', 'RESOLVED', 'TECHNICAL', '技术支持组', DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_SUB(NOW(), INTERVAL 2 DAY), DATE_SUB(NOW(), INTERVAL 2 DAY)),
(5, '用户培训请求', '客户请求安排新员工系统使用培训', 5, 'LOW', 'CLOSED', 'FEATURE', '客户成功组', DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_SUB(NOW(), INTERVAL 8 DAY), DATE_SUB(NOW(), INTERVAL 8 DAY));

-- 插入示例营销活动数据
INSERT INTO campaigns (id, name, description, type, status, start_date, end_date, budget, spent, expected_leads, actual_leads, created_at, updated_at) VALUES
(1, '春季产品推广活动', '针对新产品的春季市场推广活动', 'PAID_AD', 'RUNNING', DATE_SUB(NOW(), INTERVAL 15 DAY), DATE_ADD(NOW(), INTERVAL 15 DAY), 50000.00, 25000.00, 500, 240, NOW(), NOW()),
(2, '客户满意度调研', '年度客户满意度调研活动', 'EMAIL', 'COMPLETED', DATE_SUB(NOW(), INTERVAL 45 DAY), DATE_SUB(NOW(), INTERVAL 15 DAY), 10000.00, 8000.00, 1000, 850, NOW(), NOW()),
(3, '行业峰会参与', '参加年度行业技术峰会', 'EVENT', 'SCHEDULED', DATE_ADD(NOW(), INTERVAL 30 DAY), DATE_ADD(NOW(), INTERVAL 32 DAY), 30000.00, 0.00, 0, 0, NOW(), NOW());

-- 插入示例AI分析结果数据
INSERT INTO ai_analysis_results (id, entity_id, entity_type, customer_id, opportunity_id, analysis_type, confidence_score, prediction, predicted_value, risk_level, recommendations, analysis_data, created_at) VALUES
(1, 1, 'CUSTOMER', 1, NULL, 'CHURN_PREDICTION', 0.75, '客户有中等流失风险，建议加强沟通', NULL, 'MEDIUM', '建议安排客户经理进行深度沟通，提供专属优惠方案', '{}', NOW()),
(2, 2, 'CUSTOMER', 2, NULL, 'CROSS_SELLING_OPPORTUNITIES', 0.82, '客户有高价值交叉销售机会', NULL, 'LOW', '推荐升级到企业版服务，并增加数据分析模块', '{}', NOW()),
(3, 1, 'OPPORTUNITY', NULL, 1, 'SALES_PREDICTION', 0.85, '此销售机会有高成交概率', 50000.00, 'LOW', '建议尽快推进合同签署流程', '{}', NOW()),
(4, 5, 'OPPORTUNITY', NULL, 5, 'DETAILED_SUCCESS_ANALYSIS', 0.92, '已成交机会分析结果良好', 150000.00, 'LOW', '可作为成功案例进行宣传推广', '{}', NOW());