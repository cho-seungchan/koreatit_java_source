package com.example.ex02.mybatis;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class MyBatisConfig {
    private final ApplicationContext applicationContext;

    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig() {return new HikariConfig();}

    @Bean
    public DataSource dataSource() {return new HikariDataSource(hikariConfig());}

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        
        // 매퍼 위치 및 설정 파일 경로 지정
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));
        // 알리아스 위치 설정
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.ex02.domain.**");
   
        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        } catch (Exception e) {
            // 예외를 던져서 문제 추적
            throw new RuntimeException("Failed to create SqlSessionFactory", e);
        }
    }
    
}
