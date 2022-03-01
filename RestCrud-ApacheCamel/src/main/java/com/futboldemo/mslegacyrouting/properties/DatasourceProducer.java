
package com.futboldemo.mslegacyrouting.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "ms.configuration.ds.producer")
public class DatasourceProducer {

	private String querylist;
	private String queryOrdered;
	private String queryById;
	private String queryPost;
	private String queryPut;
	private String queryDelete;
	private String host;
	private String port;
	private String database;
	private String user;
	private String passwd;
	private String driver;


}
