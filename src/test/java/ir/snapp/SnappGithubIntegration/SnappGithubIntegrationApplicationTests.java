package ir.snapp.SnappGithubIntegration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import io.restassured.RestAssured;
import ir.snapp.github.integration.SnappGithubIntegrationApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SnappGithubIntegrationApplication.class})
class SnappGithubIntegrationApplicationTests {

    @LocalServerPort
    int port;

    @Test
    public void testCreateAccount() {
        given().port(port).basePath("/api/account/create")
                .header("Content-Type", "application/json")
                .body(testJson()).put().then().statusCode(200);
    }


    @Test
    public void testSearchLastCreatedAccount() {
        given().port(port).basePath("/api/account/search")
                .queryParam("name", "Arta")
                .get()
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", hasItem("Arta Salahesh"));
    }

    public String testJson() {
        StringBuilder contentBuilder = new StringBuilder();
        String st = null;
        try {
            File testJsonFile = new File(this.getClass().getClassLoader().getResource("mock/SnappGithubAccountTest.json").getPath());
            BufferedReader br = new BufferedReader(new FileReader(testJsonFile));
            while ((st = br.readLine()) != null)
                contentBuilder.append(st);
            return contentBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return st;
    }

}
