package ir.snapp.github.integration.restclient;

import ir.snapp.github.integration.config.ApplicationProperties;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public class GithubApiClient {
    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public GithubApiClient(RestTemplateBuilder restTemplateBuilder, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.applicationProperties = applicationProperties;
    }

    public Optional<List<String>> repoInfoByGithubAccount(String githubAccount) {

        List<String> repositoryNames = null;
        if (null == githubAccount
                || githubAccount.isEmpty())
            return Optional.empty();
        try {
            ResponseEntity<String> gitHubResponse =
                    restTemplate.getForEntity(new StringBuilder(applicationProperties.getGithub().getGithubUrl())
                            .append("/users/").append(githubAccount).append("/repos").toString(), String.class);
            String body = gitHubResponse.getBody();
            try {
                repositoryNames = new ArrayList<>();

                JSONArray jsonArray = new JSONArray(body);
                for (int i = 0; i < jsonArray.length(); i++) {
                    repositoryNames.add(jsonArray.getJSONObject(i).get("name").toString());
                }
                return Optional.ofNullable(repositoryNames);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();

    }
}
