package ir.kit.github.phonebook.repository;

import ir.kit.github.phonebook.domain.KitGithubAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class KitGithubAccountRepositoryImpl implements KitGithubAccountRepositoryCustom {


    @Autowired
    public KitGithubAccountRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    private final MongoOperations mongoOperations;



    @Override
    public List<KitGithubAccount> search(KitGithubAccount kitGithubAccount) {
        Query query = new Query();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreNullValues();
        Example<KitGithubAccount> of = Example.of(kitGithubAccount, exampleMatcher);
        query.addCriteria(Criteria.byExample(of));
        return mongoOperations.find(query, KitGithubAccount.class);
    }
}
