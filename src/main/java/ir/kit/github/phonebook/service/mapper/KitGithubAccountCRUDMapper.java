package ir.kit.github.phonebook.service.mapper;


import ir.kit.github.phonebook.domain.KitGithubAccount;
import ir.kit.github.phonebook.service.dto.KitGithubAccountDTO;
import ir.kit.github.phonebook.service.dto.KitGithubCreateUpdateDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for the entity {@link KitGithubAccount} and its DTO {@link KitGithubAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KitGithubAccountCRUDMapper extends EntityMapper<KitGithubCreateUpdateDTO, KitGithubAccount> {


    default KitGithubAccount toEntityUpdate(KitGithubAccount kitGithubAccount,KitGithubCreateUpdateDTO kitGithubAccountDTO){
        kitGithubAccount.setEmail(kitGithubAccountDTO.getEmail());
        kitGithubAccount.setGithub(kitGithubAccountDTO.getGithub());
        kitGithubAccount.setName(kitGithubAccountDTO.getName());
        kitGithubAccount.setOrganization(kitGithubAccountDTO.getOrganization());
        kitGithubAccount.setPhoneNumber(kitGithubAccountDTO.getPhoneNumber());
        return kitGithubAccount;
    }

    @Override
    default KitGithubAccount toEntity(KitGithubCreateUpdateDTO dto) {
        KitGithubAccount kitGithubAccount = new KitGithubAccount();
        kitGithubAccount.setEmail(dto.getEmail());
        kitGithubAccount.setGithub(dto.getGithub());
        kitGithubAccount.setName(dto.getName());
        kitGithubAccount.setOrganization(dto.getOrganization());
        kitGithubAccount.setPhoneNumber(dto.getPhoneNumber());
        return kitGithubAccount;
    }

    @Override
    default KitGithubCreateUpdateDTO toDto(KitGithubAccount entity) {
        KitGithubCreateUpdateDTO kitGithubCreateUpdateDTO = new KitGithubCreateUpdateDTO();
        kitGithubCreateUpdateDTO.setId(entity.getId());
        kitGithubCreateUpdateDTO.setEmail(entity.getEmail());
        kitGithubCreateUpdateDTO.setGithub(entity.getGithub());
        kitGithubCreateUpdateDTO.setName(entity.getName());
        kitGithubCreateUpdateDTO.setOrganization(entity.getOrganization());
        kitGithubCreateUpdateDTO.setPhoneNumber(entity.getPhoneNumber());
        return kitGithubCreateUpdateDTO;
    }

    @Override
    default List<KitGithubCreateUpdateDTO> toDto(List<KitGithubAccount> entityList) {
        ArrayList<KitGithubCreateUpdateDTO> kitGithubAccountDTOS = new ArrayList<>();
        for (KitGithubAccount kitGithubAccount : entityList) {
            kitGithubAccountDTOS.add(toDto(kitGithubAccount));
        }
        return kitGithubAccountDTOS;
    }
}
