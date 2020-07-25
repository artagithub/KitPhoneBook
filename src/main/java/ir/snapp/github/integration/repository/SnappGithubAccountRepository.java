package ir.snapp.github.integration.repository;

import ir.snapp.github.integration.domain.SnappGithubAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SnappGithubAccount entity.
 */
@Repository
public interface SnappGithubAccountRepository extends MongoRepository<SnappGithubAccount, String> {

}
