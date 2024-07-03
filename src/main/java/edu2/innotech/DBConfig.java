package edu2.innotech;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {
    @Value("${DataSourcePath}")
    private String dataSourcePath;
    @Value("${DBUser}")
    private String dbUser;
    @Value("${DBPassword}")
    private String dbPassword;
    @Bean
    public DataSource dataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(dataSourcePath);
        ds.setUser(dbUser);
        ds.setPassword(dbPassword);
        //System.out.println("dataSourcePath = " + dataSourcePath);
        return ds;
    }
}
