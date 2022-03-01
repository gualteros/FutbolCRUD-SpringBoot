package com.futboldemo.mslegacyrouting.configurator;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.futboldemo.mslegacyrouting.properties.DatasourceProducer;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfigurationProducer {
    @Resource
    DatasourceProducer ds;

    @Value("${VALIDATION_QUERY_DATABASE}")
    private String validationQuery;


    @Bean("dataSource")
    public DataSource datasource() {
      String url="jdbc:mysql://"+ds.getHost()+":"+ds.getPort()+"/"+ds.getDatabase();
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(ds.getDriver());
      dataSource.setUrl(url);
      dataSource.setUsername(ds.getUser());
      dataSource.setPassword(ds.getPasswd());
      dataSource.setMaxTotal(16);
      dataSource.setMinIdle(8);
      dataSource.setValidationQuery(validationQuery);
      dataSource.setValidationQueryTimeout(30);
      dataSource.setPoolPreparedStatements(true);
      dataSource.setTestOnBorrow(true);
      dataSource.setTestWhileIdle(true);
      dataSource.setTestOnReturn(true);
      dataSource.setLogAbandoned(true);
      dataSource.setRemoveAbandonedTimeout(30000);
      return dataSource;
    }


}
