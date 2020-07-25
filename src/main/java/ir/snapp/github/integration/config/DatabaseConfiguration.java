package ir.snapp.github.integration.config;

import com.github.mongobee.Mongobee;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableMongoRepositories( basePackages = "ir.snapp.github.integration.repository")
public class DatabaseConfiguration {
    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);



//    @Bean
//    public MongoClient mongo() {
//        return MongoClients.create("mongodb://localhost:27017");
//    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(  MongoClients.create("mongodb://localhost:27017"),"snappgithubintegration");
    }


}
