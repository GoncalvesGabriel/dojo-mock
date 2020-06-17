package br.com.dojos.dojomock.controller;

import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void creatAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        accountService.createAccount(createAccountDTO);
    }
}
