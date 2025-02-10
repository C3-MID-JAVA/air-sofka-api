package ec.com.airsofka.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "ec.com.airsofka.database.airsofka",
        reactiveMongoTemplateRef = "airMongoTemplate"
)
public class AirSofkaMongoConfig {

    @Value("${spring.data.mongodb.airsofka-uri}")
    private String airMongoUri;

    @Primary
    @Bean(name = "airDatabaseFactory")
    public ReactiveMongoDatabaseFactory airDatabaseFactory() {
        MongoClient mongoClient = MongoClients.create(airMongoUri);
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, "airsofka");
    }

    @Primary
    @Bean(name = "airMongoTemplate")
    public ReactiveMongoTemplate airMongoTemplate(@Qualifier("airDatabaseFactory") ReactiveMongoDatabaseFactory airDatabaseFactory) {
        return new ReactiveMongoTemplate(airDatabaseFactory);
    }
}
