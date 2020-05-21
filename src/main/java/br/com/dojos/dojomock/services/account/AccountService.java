package br.com.dojos.dojomock.services.account;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import br.com.dojos.dojomock.services.Validator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vitor.alves
 */
@Service
public class AccountService {

    private AccountRepository accountRepository;

    private Validator<CreateAccountDTO> accountValidator;

    public AccountService(AccountRepository accountRepository,
        Validator<CreateAccountDTO> accountValidator) {
        this.accountRepository = accountRepository;
        this.accountValidator = accountValidator;
    }

    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        this.accountValidator.validar(createAccountDTO);
        Account account = createAccountDTO.createAccount();
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Nao existe conta para esse id");
        }
    }
}
