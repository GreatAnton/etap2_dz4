package edu2.innotech;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootTest
@EnableTransactionManagement
public class DBConfigTest {
    //@Value("${DataSourcePath}")
    private String dataSourcePath = "jdbc:h2:.\\db\\h2_test\\dz4";
    //@Value("${DBUser}")
    private String dbUser = "sa_test";
    //@Value("${DBPassword}")
    private String dbPassword = "2";
    @Bean
    public DataSource dataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(dataSourcePath);
        ds.setUser(dbUser);
        ds.setPassword(dbPassword);
        System.out.println("dataSourcePath_test = " + dataSourcePath);
        return ds;
    }
}
