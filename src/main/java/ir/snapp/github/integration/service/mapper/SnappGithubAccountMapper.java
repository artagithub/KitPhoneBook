package ir.snapp.github.integration.service.mapper;


import ir.snapp.github.integration.domain.*;
import ir.snapp.github.integration.service.dto.SnappGithubAccountDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for the entity {@link SnappGithubAccount} and its DTO {@link SnappGithubAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SnappGithubAccountMapper extends EntityMapper<SnappGithubAccountDTO, SnappGithubAccount> {

    @Override
    default SnappGithubAccount toEntity(SnappGithubAccountDTO dto) {
        SnappGithubAccount snappGithubAccount = new SnappGithubAccount();
        snappGithubAccount.setEmail(dto.getEmail());
        snappGithubAccount.setGithub(dto.getGithub());
        snappGithubAccount.setName(dto.getName());
        snappGithubAccount.setOrganization(dto.getOrganization());
        snappGithubAccount.setPhoneNumber(dto.getPhoneNumber());
        return snappGithubAccount;
    }

    @Override
    default SnappGithubAccountDTO toDto(SnappGithubAccount entity) {
        SnappGithubAccountDTO snappGithubAccountDTO = new SnappGithubAccountDTO();
        snappGithubAccountDTO.setEmail(entity.getEmail());
        snappGithubAccountDTO.setGithub(entity.getGithub());
        snappGithubAccountDTO.setName(entity.getName());
        snappGithubAccountDTO.setOrganization(entity.getOrganization());
        snappGithubAccountDTO.setPhoneNumber(entity.getPhoneNumber());
        return snappGithubAccountDTO;
    }

    @Override
    default List<SnappGithubAccountDTO> toDto(List<SnappGithubAccount> entityList) {
        ArrayList<SnappGithubAccountDTO> snappGithubAccountDTOS = new ArrayList<>();
        for (SnappGithubAccount snappGithubAccount : entityList) {
            snappGithubAccountDTOS.add(toDto(snappGithubAccount));
        }
        return snappGithubAccountDTOS;
    }
}
