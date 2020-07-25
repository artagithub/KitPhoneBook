package ir.snapp.github.integration.service;

import ir.snapp.github.integration.domain.SnappGithubAccount;
import ir.snapp.github.integration.repository.SnappGithubAccountRepository;
import ir.snapp.github.integration.restclient.GithubApiClient;
import ir.snapp.github.integration.service.dto.SnappGithubAccountDTO;
import ir.snapp.github.integration.service.mapper.SnappGithubAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Service Implementation for managing {@link SnappGithubAccount}.
 */
@Service
public class SnappGithubAccountService {

    private final Logger log = LoggerFactory.getLogger(SnappGithubAccountService.class);

    private final SnappGithubAccountRepository snappGithubAccountRepository;

    private final SnappGithubAccountMapper snappGithubAccountMapper;

    private final GithubApiClient githubApiClient;

    private final MongoOperations mongoOperations;


    public SnappGithubAccountService(SnappGithubAccountRepository snappGithubAccountRepository
            , SnappGithubAccountMapper snappGithubAccountMapper
            , GithubApiClient githubApiClient
    , MongoOperations mongoOperations) {
        this.snappGithubAccountRepository = snappGithubAccountRepository;
        this.snappGithubAccountMapper = snappGithubAccountMapper;
        this.githubApiClient = githubApiClient;
        this.mongoOperations = mongoOperations;
    }

    /**
     * Save or update a snappGithubAccount.Spring Unit of work approach...
     *
     * @param snappGithubAccountDTO the entity to save.
     * @return the persisted entity.
     */
    public CompletableFuture<SnappGithubAccountDTO> save(SnappGithubAccountDTO snappGithubAccountDTO) {
        log.debug("Request to save SnappGithubAccount : {}", snappGithubAccountDTO);
        SnappGithubAccount snappGithubAccount = snappGithubAccountMapper.toEntity(snappGithubAccountDTO);
        Optional<List<String>> repositoryNames = githubApiClient.repoInfoByGithubAccount(snappGithubAccount.getGithub());
        snappGithubAccount.setGithubRepoNames(repositoryNames.orElse(new ArrayList<>()));
        snappGithubAccount = snappGithubAccountRepository.save(snappGithubAccount);
        return CompletableFuture.completedFuture(snappGithubAccountMapper.toDto(snappGithubAccount));
    }


    /**
     * Search by repo names
     *
     * @param snappGithubAccountDTO the dto.
     * @return the list of entities.
     */
    public List<SnappGithubAccount> search(SnappGithubAccountDTO snappGithubAccountDTO) {
        SnappGithubAccount snappGithubAccount = snappGithubAccountMapper.toEntity(snappGithubAccountDTO);
        Query query = new Query();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreNullValues();
        Example<SnappGithubAccount> of = Example.of(snappGithubAccount, exampleMatcher);
        query.addCriteria(Criteria.byExample(of));
        return mongoOperations.find(query, SnappGithubAccount.class);

    }

}
