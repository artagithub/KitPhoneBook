package ir.snapp.github.integration.rest;

import ir.snapp.github.integration.domain.SnappGithubAccount;
import ir.snapp.github.integration.service.SnappGithubAccountService;
import ir.snapp.github.integration.service.dto.SnappGithubAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * REST controller for managing {@link ir.snapp.github.integration.domain.SnappGithubAccount}.
 */
@RestController
@RequestMapping("/api/account")
public class SnappGithubAccountResource {

    private final Logger log = LoggerFactory.getLogger(ir.snapp.github.integration.rest.SnappGithubAccountResource.class);

    private final SnappGithubAccountService snappGithubAccountService;

    public SnappGithubAccountResource(SnappGithubAccountService snappGithubAccountService) {
        this.snappGithubAccountService = snappGithubAccountService;
    }

    /**
     * {@code PUT  /create} : Create a new snappGithubAccount.
     *
     * @param snappGithubAccountDTO the snappGithubAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new snappGithubAccountDTO, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create")
    public @ResponseBody
    CompletableFuture<SnappGithubAccountDTO> createSnappGithubAccount(@Valid @RequestBody SnappGithubAccountDTO snappGithubAccountDTO) throws URISyntaxException {
        log.debug("REST request to save or update SnappGithubAccount : {}", snappGithubAccountDTO);
        return snappGithubAccountService.save(snappGithubAccountDTO);
    }


    /**
     * {@code GET  /search} : Search for accounts.
     *
     * @param snappGithubAccountDTO the snappGithubAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Found)} and with body the new snappGithubAccountDTO, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @GetMapping( path = "/search", produces = "application/json")
    public ResponseEntity<List<SnappGithubAccount>> find(SnappGithubAccountDTO snappGithubAccountDTO){
        return ResponseEntity.ok(snappGithubAccountService.search(snappGithubAccountDTO));
    }

}
