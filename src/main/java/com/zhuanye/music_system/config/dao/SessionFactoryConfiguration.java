package com.zhuanye.music_system.config.dao;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 数据库sqlSession配置类
 */

@Configuration
public class SessionFactoryConfiguration {

    @Value("${mapper_path}")
    private String mapperPath;

    @Value("${mybatis_config_file}")
    private String mybatisConfigFilePath;

    @Autowired
    private DataSource dataSouce;
    @Value("${entity_package}")
    private String entityPackage;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSouce);
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }

}
