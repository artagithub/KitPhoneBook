package ir.kit.github.phonebook.service;

import ir.kit.github.phonebook.domain.KitGithubAccount;
import ir.kit.github.phonebook.repository.KitGithubAccountRepository;
import ir.kit.github.phonebook.restclient.GithubApiClient;
import ir.kit.github.phonebook.service.dto.KitGithubAccountDTO;
import ir.kit.github.phonebook.service.mapper.KitGithubAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Service Implementation for managing {@link KitGithubAccount}.
 */
@Service
public class KitGithubAccountService {

    private final Logger log = LoggerFactory.getLogger(KitGithubAccountService.class);

    private final KitGithubAccountRepository kitGithubAccountRepository;

    private final KitGithubAccountMapper kitGithubAccountMapper;

    private final GithubApiClient githubApiClient;




    public KitGithubAccountService(KitGithubAccountRepository kitGithubAccountRepository
            , KitGithubAccountMapper kitGithubAccountMapper
            , GithubApiClient githubApiClient
    ) {
        this.kitGithubAccountRepository = kitGithubAccountRepository;
        this.kitGithubAccountMapper = kitGithubAccountMapper;
        this.githubApiClient = githubApiClient;
    }

    /**
     * Save a kitGithubAccount.Spring Unit of work approach...
     *
     * @param kitGithubAccountDTO the entity to save.
     * @return the persisted entity.
     */
    public CompletableFuture<KitGithubAccountDTO> save(KitGithubAccountDTO kitGithubAccountDTO) {
        log.debug("Request to save KitGithubAccount : {}", kitGithubAccountDTO);
        KitGithubAccount kitGithubAccount = kitGithubAccountMapper.toEntity(kitGithubAccountDTO);
        Optional<List<String>> repositoryNames = githubApiClient.repoInfoByGithubAccount(kitGithubAccount.getGithub());
        kitGithubAccount.setGithubRepoNames(repositoryNames.orElse(new ArrayList<>()));
        kitGithubAccount = kitGithubAccountRepository.save(kitGithubAccount);
        return CompletableFuture.completedFuture(kitGithubAccountMapper.toDto(kitGithubAccount));
    }

    /**
     * Update a kitGithubAccount.Spring Unit of work approach...
     *
     * @param kitGithubAccountDTO the entity to update.
     * @return the updated entity.
     */
    public CompletableFuture<KitGithubAccountDTO> update(KitGithubAccountDTO kitGithubAccountDTO) {
        log.debug("Request to update KitGithubAccount : {}", kitGithubAccountDTO);
        Optional<KitGithubAccount> kitGithubAccountFinded = kitGithubAccountRepository.findById(kitGithubAccountDTO.getId());
        if(kitGithubAccountFinded.isPresent()) {
            Optional<List<String>> repositoryNames = Optional.empty();
            if (!kitGithubAccountFinded.get().getGithub().equals(kitGithubAccountDTO.getGithub())) {
                repositoryNames = githubApiClient.repoInfoByGithubAccount(kitGithubAccountDTO.getGithub());
            }
            KitGithubAccount kitGithubAccount = kitGithubAccountMapper.toEntityUpdate(kitGithubAccountFinded.orElse(new KitGithubAccount())
                    , kitGithubAccountDTO);
            kitGithubAccount.setGithubRepoNames(repositoryNames.orElse(kitGithubAccountFinded.get().getGithubRepoNames()));
            kitGithubAccount.setId(kitGithubAccountDTO.getId());
            kitGithubAccount = kitGithubAccountRepository.save(kitGithubAccount);
            return CompletableFuture.completedFuture(kitGithubAccountMapper.toDto(kitGithubAccount));
        }
        return CompletableFuture.completedFuture(new KitGithubAccountDTO());
    }


    /**
     * Search by repo names
     *
     * @param kitGithubAccountDTO the dto.
     * @return the list of entities.
     */
    public List<KitGithubAccountDTO> search(KitGithubAccountDTO kitGithubAccountDTO) {
        log.debug("Request to search KitGithubAccount : {}", kitGithubAccountDTO);
        KitGithubAccount kitGithubAccount = kitGithubAccountMapper.toEntity(kitGithubAccountDTO);
        List<KitGithubAccount> findedKitGithubAccounts = kitGithubAccountRepository.search(kitGithubAccount);
        List<KitGithubAccountDTO> kitGithubAccountDTOS = new ArrayList<>();
        if (null!= findedKitGithubAccounts&& !findedKitGithubAccounts.isEmpty()){
            for (KitGithubAccount findedKitGithubAccount : findedKitGithubAccounts) {
                kitGithubAccountDTOS.add(kitGithubAccountMapper.toDto(findedKitGithubAccount));
            }
        }

        return kitGithubAccountDTOS;

    }


    /**
     * return All
     *
     * @return the list of entities.
     */
    public List<KitGithubAccountDTO> findAll() {
        log.debug("Request to find all KitGithubAccounts");
        List<KitGithubAccount> findedKitGithubAccounts = kitGithubAccountRepository.findAll();
        List<KitGithubAccountDTO> kitGithubAccountDTOS = new ArrayList<>();
        if (null!= findedKitGithubAccounts&& !findedKitGithubAccounts.isEmpty()){
            for (KitGithubAccount findedKitGithubAccount : findedKitGithubAccounts) {
                kitGithubAccountDTOS.add(kitGithubAccountMapper.toDto(findedKitGithubAccount));
            }
        }
        return kitGithubAccountDTOS;
    }



    /**
     * Delete Kit Account
     *
     * @return the deleted entity.
     */
    public KitGithubAccountDTO delete(String id) {
        log.debug("Request to delete KitGithubAccounts");
        Optional<KitGithubAccount> kitGithubAccountFinded = kitGithubAccountRepository.findById(id);
        if(kitGithubAccountFinded.isPresent()){
            KitGithubAccount kitGithubAccount = kitGithubAccountFinded.get();
            kitGithubAccountRepository.deleteById(id);
            return kitGithubAccountMapper.toDto(kitGithubAccount);
        }
        return new KitGithubAccountDTO();
    }

}
