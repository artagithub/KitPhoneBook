package ir.kit.github.phonebook.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.kit.github.phonebook.domain.KitGithubAccount;
import ir.kit.github.phonebook.service.KitGithubAccountService;
import ir.kit.github.phonebook.service.dto.KitGithubAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * REST controller for managing {@link KitGithubAccount}.
 */
@RestController
@RequestMapping("/api/account")
public class KitGithubAccountResource {

    private final Logger log = LoggerFactory.getLogger(KitGithubAccountResource.class);

    private final KitGithubAccountService kitGithubAccountService;

    public KitGithubAccountResource(KitGithubAccountService kitGithubAccountService) {
        this.kitGithubAccountService = kitGithubAccountService;
    }

    /**
     * {@code POST  /create} : Create a new kitGithubAccount.
     *
     * @param kitGithubAccountDTO the kitGithubAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kitGithubAccountDTO, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Operation(summary = "Create new PhoneBook Account")
    @ApiResponses(value = {@ApiResponse(responseCode= "200",content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = KitGithubAccountDTO.class) )
    },description = "Success")
    ,@ApiResponse(responseCode = "400",description = "Not Found"),@ApiResponse(responseCode = "500",description = "Internal Server Error")})
    @PostMapping("/create")
    public @ResponseBody
    CompletableFuture<KitGithubAccountDTO> createKitGithubAccount(@Valid @RequestBody KitGithubAccountDTO kitGithubAccountDTO) throws URISyntaxException {
        log.debug("REST request to save  KitGithubAccount : {}", kitGithubAccountDTO);
        return kitGithubAccountService.save(kitGithubAccountDTO);
    }

    /**
     * {@code PUT  /update} : Update kitGithubAccount.
     *
     * @param kitGithubAccountDTO the kitGithubAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kitGithubAccountDTO, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Operation(summary = "Update PhoneBook Account")
    @ApiResponses(value = {@ApiResponse(responseCode= "200",content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = KitGithubAccountDTO.class) )
    },description = "Success")
            ,@ApiResponse(responseCode = "400",description = "Not Found"),@ApiResponse(responseCode = "500",description = "Internal Server Error")})
    @PutMapping("/update")
    public @ResponseBody
    CompletableFuture<KitGithubAccountDTO> updateKitGithubAccount(@Valid @RequestBody KitGithubAccountDTO kitGithubAccountDTO) throws URISyntaxException {
        log.debug("REST request to update KitGithubAccount : {}", kitGithubAccountDTO);
        return kitGithubAccountService.update(kitGithubAccountDTO);
    }

    /**
     * {@code GET  /search} : Search for accounts.
     *
     * @param kitGithubAccountDTO the kitGithubAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Found)} and with body the new kitGithubAccountDTO, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Operation(summary = "Search PhoneBook Account")
    @ApiResponses(value = {@ApiResponse(responseCode= "200",content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = KitGithubAccountDTO.class) )
    },description = "Success")
            ,@ApiResponse(responseCode = "400",description = "Not Found"),@ApiResponse(responseCode = "500",description = "Internal Server Error")})
    @GetMapping( path = "/search", produces = "application/json")
    public ResponseEntity<List<KitGithubAccountDTO>> search(KitGithubAccountDTO kitGithubAccountDTO){
        return ResponseEntity.ok(kitGithubAccountService.search(kitGithubAccountDTO));
    }


    /**
     * {@code GET  /list} : Return all the contacts
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Found)} and with body the new kitGithubAccountDTO, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Operation(summary = "List all PhoneBook Account")
    @ApiResponses(value = {@ApiResponse(responseCode= "200",content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = KitGithubAccountDTO.class) )
    },description = "Success")
            ,@ApiResponse(responseCode = "400",description = "Not Found"),@ApiResponse(responseCode = "500",description = "Internal Server Error")})
    @GetMapping( path = "/list", produces = "application/json")
    public ResponseEntity<List<KitGithubAccountDTO>> list(){
        return ResponseEntity.ok(kitGithubAccountService.findAll());
    }


    /**
     * {@code DELETE  /delete} : Return the deleted account
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Found)} and with body the new kitGithubAccountDTO, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Operation(summary = "Delete PhoneBook Account")
    @ApiResponses(value = {@ApiResponse(responseCode= "200",content = {
            @Content(mediaType = "application/json",schema = @Schema(implementation = KitGithubAccountDTO.class) )
    },description = "Success")
            ,@ApiResponse(responseCode = "400",description = "Not Found"),@ApiResponse(responseCode = "500",description = "Internal Server Error")})
    @DeleteMapping( path = "/delete", produces = "application/json")
    public ResponseEntity<KitGithubAccountDTO> delete(@RequestParam String id){
        return ResponseEntity.ok(kitGithubAccountService.delete(id));
    }

}
