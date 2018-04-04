# 安装配置说明
keycloak密码和用户存储提供策略
1. 在modules/system/layers/keycloak文件夹中增加相应数据库jdbc的jar文件和目录
2. 在standalone/configuration中的standalone.xml文件搜索datasource并修改pool-name为KeycloakDS的datasource节点，
   同时在drivers增加相应的数据库driver，例如
```xml
<datasources>
  <datasource jndi-name="java:jboss/datasources/KeycloakDS" pool-name="KeycloakDS" enabled="true" use-java-context="true">
      <connection-url>jdbc:sqlserver://xxx.xxx.xx.xx:xxxx;DatabaseName=XXX</connection-url>
      <driver>sqlserver</driver>
      <security>
          <user-name>XX</user-name>
          <password>XX</password>
      </security>
  </datasource>
  <drivers>
      <driver name="sqlserver" module="com.microsoft.sqlserver">
          <xa-datasource-class>com.microsoft.sqlserver.jdbc.SQLServerDataSource</xa-datasource-class>
      </driver>
      <driver name="h2" module="com.h2database.h2">
          <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
      </driver>
  </drivers>
</datasources>
```
3. 复制生成的jar包到standalone/deployments文件夹
