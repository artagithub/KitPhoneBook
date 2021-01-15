package ir.kit.github.phonebook.service.mapper;


import ir.kit.github.phonebook.domain.KitGithubAccount;
import ir.kit.github.phonebook.service.dto.KitGithubAccountDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for the entity {@link KitGithubAccount} and its DTO {@link KitGithubAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KitGithubAccountMapper extends EntityMapper<KitGithubAccountDTO, KitGithubAccount> {


    default KitGithubAccount toEntityUpdate(KitGithubAccount kitGithubAccount,KitGithubAccountDTO kitGithubAccountDTO){
        kitGithubAccount.setEmail(kitGithubAccountDTO.getEmail());
        kitGithubAccount.setGithub(kitGithubAccountDTO.getGithub());
        kitGithubAccount.setName(kitGithubAccountDTO.getName());
        kitGithubAccount.setOrganization(kitGithubAccountDTO.getOrganization());
        kitGithubAccount.setPhoneNumber(kitGithubAccountDTO.getPhoneNumber());
        return kitGithubAccount;
    }

    @Override
    default KitGithubAccount toEntity(KitGithubAccountDTO dto) {
        KitGithubAccount kitGithubAccount = new KitGithubAccount();
        kitGithubAccount.setEmail(dto.getEmail());
        kitGithubAccount.setGithub(dto.getGithub());
        kitGithubAccount.setName(dto.getName());
        kitGithubAccount.setOrganization(dto.getOrganization());
        kitGithubAccount.setPhoneNumber(dto.getPhoneNumber());
        return kitGithubAccount;
    }

    @Override
    default KitGithubAccountDTO toDto(KitGithubAccount entity) {
        KitGithubAccountDTO kitGithubAccountDTO = new KitGithubAccountDTO();
        kitGithubAccountDTO.setId(entity.getId());
        kitGithubAccountDTO.setEmail(entity.getEmail());
        kitGithubAccountDTO.setGithub(entity.getGithub());
        kitGithubAccountDTO.setName(entity.getName());
        kitGithubAccountDTO.setOrganization(entity.getOrganization());
        kitGithubAccountDTO.setPhoneNumber(entity.getPhoneNumber());
        return kitGithubAccountDTO;
    }

    @Override
    default List<KitGithubAccountDTO> toDto(List<KitGithubAccount> entityList) {
        ArrayList<KitGithubAccountDTO> kitGithubAccountDTOS = new ArrayList<>();
        for (KitGithubAccount kitGithubAccount : entityList) {
            kitGithubAccountDTOS.add(toDto(kitGithubAccount));
        }
        return kitGithubAccountDTOS;
    }
}
