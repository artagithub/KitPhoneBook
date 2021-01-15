package ir.kit.github.phonebook.repository;

import ir.kit.github.phonebook.domain.KitGithubAccount;
import ir.kit.github.phonebook.service.dto.KitGithubAccountDTO;

import java.util.List;

public interface KitGithubAccountRepositoryCustom {

    List<KitGithubAccount> search(KitGithubAccount kitGithubAccount);
}
