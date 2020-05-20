package br.com.dojos.dojomock.services;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author vitor.alves
 */
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        validaDocumentNumberExistente(createAccountDTO);
        Account account = createAccountDTO.createAccount();
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    public void validaDocumentNumberExistente(CreateAccountDTO createAccountDTO) {
        Optional<Account> account = accountRepository.findByDocumentNumber(createAccountDTO.getDocumentNumber());

        if(account.isPresent()){
            throw new RuntimeException("Document Number already exist.");
        }
    }
}
