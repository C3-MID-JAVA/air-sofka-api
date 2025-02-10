package ec.com.airsofka.config;


import ec.com.airsofka.IJSONMapper;
import ec.com.airsofka.JSONMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public IJSONMapper jsonMapper(){
        return new JSONMap();
    }
}
