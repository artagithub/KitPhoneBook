package ir.snapp.github.integration.service.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * A DTO for the {@link ir.snapp.github.integration.domain.SnappGithubAccount} entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class SnappGithubAccountDTO implements Serializable {
    
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
