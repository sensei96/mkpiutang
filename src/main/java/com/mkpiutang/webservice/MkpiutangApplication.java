package com.mkpiutang.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
//import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//import com.test.DemoApplication;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.mkpiutang")
//@EntityScan(basePackages="com.mkpiutang.model")
public class MkpiutangApplication extends SpringBootServletInitializer{

	@Autowired
	private Environment env;
	
	 private static Class<MkpiutangApplication> mkpiutangClass = MkpiutangApplication.class;
	    	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MkpiutangApplication.class);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(MkpiutangApplication.class, args);
    }
    
    @Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		ds.setUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		return ds;
	}
    
}
