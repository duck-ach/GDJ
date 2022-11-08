package com.gdu.app09.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



/*
	트랜잭션은 언제 필요한가요?
	하나의 서비스에서 2개 이상의 INSERT/UPDATE/DELETE가 호출되는 경우에 필요합니다.
*/

/*
	@PropertySource
	안녕. 난 프로퍼티 파일을 참조할 수 있는 애너테이션이야.
*/
@PropertySource(value = {"classpath:mybatis/config/db.properties"})
/*
	@EnableTransactionManagement
	안녕. 난 트랜잭션 매니저를 허용하는 애너테이션이야.
 */
@EnableTransactionManagement

/*
	@EnableAspectJAutoProxy
	안녕. 난 Aspect를 자동으로 동작시키는 애너테이션이야.
*/
@EnableAspectJAutoProxy

@Configuration
public class DBConfig {

	// SpringJdbc 처리를 위한 DriverManagerDataSource와 JdbcTemplate을 Bean으로 등록한다.
	
	// db.properties 파일을 읽어서 변수에 저장하기
	// ${프로퍼티명}
    @Value(value = "${hikari.driver}")
    private String driver;
    
    @Value(value="${hikari.url}")
    private String url;
    
    @Value(value="${hikari.username}")
    private String username;
    
    @Value(value="${hikari.password}")
    private String password;
    
    // HikariConfig
    @Bean
    public HikariConfig config() {
    	HikariConfig config = new HikariConfig();
    	config.setDriverClassName(driver);
    	config.setJdbcUrl(url);
    	config.setUsername(username);
    	config.setPassword(password);
    	return config;
    }
    
    // HikariDataSource
    @Bean
    public HikariDataSource dataSource() {
    	return new HikariDataSource(config());
    }
    
    // sqlSessionFactory (sql을 뽑아내기 위함)
    @Bean // Mybatis의 과거이름이 ibatis였어서 과거 이름으로 패키지가 형성되어있음
    public SqlSessionFactory factory() throws Exception {
    	SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    	bean.setDataSource(dataSource()); // HikariCP가 지원하는 dataSource()를 호출    // *.xml을 하면 모든 xml을 가져올 수 있다.
    	bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mybatis/mapper/*.xml")); // mybatis의 mapper 경로
    	bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mybatis/config/mybatis-config.xml"));
    	return bean.getObject(); // factory를 꺼냄
    }
    
    // sqlSessionTemplate
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    	return new SqlSessionTemplate(factory());
    }
    
    
    // Transaction 처리를 위한 TransactionManager를 Bean으로 등록한다.
    @Bean
    public TransactionManager transactionManager() {
    	return new DataSourceTransactionManager(dataSource());
    } // dataSource를 불러와서 처리
    

}