# micro-servicce-2022

# SpringBatch
<pre>
<b>MySQL 資料庫建立：</b>
CREATE DATABASE springbatch DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci

<b>pom.xml：</b>
&lt;dependency&gt;
    &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
    &lt;artifactId&gt;spring-boot-starter-batch&lt;/artifactId&gt;
&lt;/dependency&gt;
&lt;!-- 使用 Spring Batch 加入  MySQL 資料庫依賴 --&gt;
&lt;dependency&gt;
    &lt;groupId&gt;mysql&lt;/groupId&gt;
    &lt;artifactId&gt;mysql-connector-java&lt;/artifactId&gt;
&lt;/dependency&gt;
&lt;dependency&gt;
    &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
    &lt;artifactId&gt;spring-boot-starter-jdbc&lt;/artifactId&gt;
&lt;/dependency&gt;

<b>application.yml：</b>
spring:
  datasource:
    username: root
    url: jdbc:mysql://localhost:3306/springbatch
    driverClassName: com.mysql.cj.jdbc.Driver
    password: '12345678'
  batch:
    jdbc:
      initialize-schema: always
  sql:
    init:
      schema-locations: classpath:/org/springframework/batch/core/schema-mysql.sql

</pre>
https://www.javainuse.com/spring/cloud-gateway-eureka

