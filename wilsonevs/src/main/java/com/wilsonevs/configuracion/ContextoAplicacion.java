package com.wilsonevs.configuracion;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.awt.color.ProfileDataException;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("file:/xampp/htdocs/FonYou/CRUD_API_REST/configuracion/db.properties")
@ComponentScan("com.wilsonevs")
public class ContextoAplicacion {

    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment env) {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
        } catch (IllegalStateException | ProfileDataException ex) {
            throw new RuntimeException("error while setting the driver class name in the datasource", ex);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        ds.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
        ds.setUser(env.getRequiredProperty("jdbc.username"));
        ds.setPassword(env.getRequiredProperty("jdbc.password"));
        ds.setInitialPoolSize(env.getRequiredProperty("c3p0.initial_pool_size", Integer.class));
        ds.setMinPoolSize(env.getRequiredProperty("c3p0.min_size", Integer.class));
        ds.setMaxPoolSize(env.getRequiredProperty("c3p0.max_size", Integer.class));
        ds.setMaxStatements(env.getRequiredProperty("c3p0.max_statements", Integer.class));
        ds.setAcquireIncrement(env.getRequiredProperty("c3p0.acquire_increment", Integer.class));
        ds.setAcquireRetryAttempts(env.getRequiredProperty("c3p0.acquire_retry_attempts", Integer.class));
        ds.setMaxIdleTime(env.getRequiredProperty("c3p0.max_idle_time", Integer.class));
        ds.setUnreturnedConnectionTimeout(env.getRequiredProperty("c3p0.unreturned_connection_timeout", Integer.class));
        return ds;
    }

    @Bean
    JdbcTemplate jdbcTemplate (DataSource dataSource) {
        return new JdbcTemplate((dataSource));
    }

    @Bean
    DataSourceTransactionManager dataSourceTransactionManager (DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
