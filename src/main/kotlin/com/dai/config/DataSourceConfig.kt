package com.dai.config

import com.alibaba.druid.pool.DruidDataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@MapperScan(basePackages = arrayOf("com.dai.dao"), sqlSessionFactoryRef = "sqlSessionFactory")

@EnableTransactionManagement
// 自己添加的，指定配置文件
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
class DataSourceConfig
constructor() {

    @Value("\${spring.datasource.driver-class-name}")
    private val driverName: String? = null
    @Value("\${spring.datasource.username}")
    private val dbUser: String? = null
    @Value("\${spring.datasource.url}")
    private val dbUrl: String? = null
    @Value("\${spring.datasource.password}")
    private val dbPassword: String? = null

    @Autowired
    private var resourceLoader: ResourceLoader? = null

    @Bean(name = arrayOf("dataSource"))
    fun settleDataSource(): DataSource {

        val dataSource = DruidDataSource()
        dataSource.driverClassName = driverName
        dataSource.url = dbUrl
        dataSource.username = dbUser
        dataSource.password = dbPassword
        dataSource.isPoolPreparedStatements = true
        dataSource.maxWait = 60000
        dataSource.maxActive = 20
        dataSource.initialSize = 10
        dataSource.maxPoolPreparedStatementPerConnectionSize = 20
        dataSource.minIdle = 10
        dataSource.minEvictableIdleTimeMillis = 300000
        dataSource.timeBetweenEvictionRunsMillis = 60000
        //        dataSource.setValidationQuery("SELECT 1");
        dataSource.isTestWhileIdle = true
        dataSource.isTestOnReturn = false
        dataSource.isTestOnBorrow = false
        return dataSource
    }

    @Bean(name = arrayOf("transactionManager"))
    fun settleTransactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(settleDataSource())
    }

    @Bean(name = arrayOf("sqlSessionFactory"))
    @Throws(Exception::class)
    fun settleSqlSessionFactory(@Qualifier("dataSource") settleDataSource: DataSource): SqlSessionFactory {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(settleDataSource)
        sessionFactory.setMapperLocations(
                ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath:mapper/*.xml"))
        return sessionFactory.`object`
    }

}