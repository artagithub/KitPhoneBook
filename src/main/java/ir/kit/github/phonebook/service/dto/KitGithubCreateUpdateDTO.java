package ir.kit.github.phonebook.service.dto;

import ir.kit.github.phonebook.domain.KitGithubAccount;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * A DTO for the {@link KitGithubAccount} entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class KitGithubCreateUpdateDTO {

    private String id;

    @NotNull
    private String name;


    @Pattern(regexp = "^(\\+98|0)+9\\d{9}$")
    private String phoneNumber;


    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;

    private String organization;

    @NotNull
    private String github;

}
