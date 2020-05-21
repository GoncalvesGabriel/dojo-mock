package br.com.dojos.dojomock.services.account;

import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import br.com.dojos.dojomock.services.Validator;
import java.util.Optional;

/**
 * @author vitor.alves
 */
public class PreAccountValidator implements Validator<CreateAccountDTO> {

    private AccountRepository accountRepository;

    public PreAccountValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void validar(CreateAccountDTO dado) {
        this.validaDocumentNumberExistente(dado);
    }

    public void validaDocumentNumberExistente(CreateAccountDTO createAccountDTO) {
        Optional<Account> account = accountRepository.findByDocumentNumber(createAccountDTO.getDocumentNumber());

        if(account.isPresent()){
            throw new RuntimeException("Document Number already exist.");
        }
    }
}
