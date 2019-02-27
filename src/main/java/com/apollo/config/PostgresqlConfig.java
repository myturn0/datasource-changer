package com.apollo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/27
 */
@Configuration
@MapperScan(basePackages = "com.apollo.dao.pg",
        sqlSessionTemplateRef = "pgSqlSessionTemplate")
public class PostgresqlConfig {

    /**
     * postgresql数据源
     */
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSource pgDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @DependsOn("pgDataSource")
    public SqlSessionFactory pgSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(pgDataSource());
        return factoryBean.getObject();
    }

    /**
     * DefaultSqlSession和SqlSessionTemplate都实现了SqlSession,但我们
     * 注入线程安全的SqlSessionTemplate,而不使用默认的线程不安全的DefaultSqlSession
     */
    @Bean
    @DependsOn("pgSqlSessionFactory")
    public SqlSessionTemplate pgSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(pgSqlSessionFactory());
    }
}
