package dev.traning.securitysettings.securityconfig;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {
    @Bean
    DataSource dataSourceSecurity() throws SQLException {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/auth")
                .password("locations")
                .username("root")
                .build();



    }
}
