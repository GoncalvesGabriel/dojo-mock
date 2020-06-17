package br.com.dojos.dojomock.controller;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.services.account.AccountService;
import com.sun.jndi.toolkit.url.Uri;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vitor.alves
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        AccountDTO account = accountService.createAccount(createAccountDTO);
        URI uri = URI.create(String.format("/accounts/%s",account.getId()));
        return ResponseEntity.created(uri).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findAccount(@PathVariable long id){
        Optional<AccountDTO> optionalAccount = accountService.findDTOById(id);
        return ResponseEntity.of(optionalAccount);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
