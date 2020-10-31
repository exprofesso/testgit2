package evilNerd.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    @Value("${driveName}")
    private String driveName;

    @Value("${url}")
    private String url;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;
}