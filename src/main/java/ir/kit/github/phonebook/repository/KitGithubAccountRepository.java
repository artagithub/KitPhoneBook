package ir.kit.github.phonebook.repository;

import ir.kit.github.phonebook.domain.KitGithubAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the KitGithubAccount entity.
 */
@Repository
public interface KitGithubAccountRepository extends MongoRepository<KitGithubAccount, String>, KitGithubAccountRepositoryCustom {

}
