package com.yx.base.spring.boot.conf;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

//@Configuration
@Deprecated
public class MybatisConfig {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = null;
        try {
        	// 加载JNDI配置
            //Context context = new InitialContext();
            //DataSource dataSource = (DataSource)context.lookup(dataSourceJndiName);

            // 实例SessionFactory
            sqlSessionFactoryBean = new SqlSessionFactoryBean();
            // 配置数据源
            sqlSessionFactoryBean.setDataSource(dataSource);

            // 加载MyBatis配置文件
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            
    		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*Mapper.xml"));
            sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis/conf/Configuration.xml"));

        } catch (Exception e) {
            logger.error("创建mybatis的SqlSession连接工厂错误：{}", e);
        }
        
        return sqlSessionFactoryBean;
    }


}
