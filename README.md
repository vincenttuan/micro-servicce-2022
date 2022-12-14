# micro-servicce-2022

# SpringBatch
<pre>
<b>MySQL 資料庫建立：</b>
CREATE DATABASE springbatch DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci

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
