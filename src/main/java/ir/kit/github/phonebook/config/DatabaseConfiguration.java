package ir.kit.github.phonebook.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@Configuration
@EnableMongoRepositories( basePackages = "ir.kit.github.phonebook.repository"
,repositoryImplementationPostfix = "Impl")
public class DatabaseConfiguration {
    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);



//    @Bean
//    public MongoClient mongo() {
//        return MongoClients.create("mongodb://localhost:27017");
//    }

    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "kitgithubphonebook";

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }

//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(  MongoClients.create("mongodb://localhost:27017"),"kitgithubphonebook");
//    }


}
