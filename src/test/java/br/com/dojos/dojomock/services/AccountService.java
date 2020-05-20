package br.com.dojos.dojomock.services;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;

/**
 * @author vitor.alves
 */
public class AccountService {

    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        Account account = createAccountDTO.createAccount();
        account.setId(1L);
        return new AccountDTO(account);
    }
}
