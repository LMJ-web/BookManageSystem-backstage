package com.LMJ.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.LMJ.service","com.LMJ.dao"})
@PropertySource("classpath:db.properties")
@Import({DataSourceConfig.class,MybatisConfig.class})
public class SpringConfig {
}
