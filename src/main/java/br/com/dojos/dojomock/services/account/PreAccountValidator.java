package br.com.dojos.dojomock.services.account;

import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import br.com.dojos.dojomock.services.Validator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author vitor.alves
 */
@Component
public class PreAccountValidator implements Validator<CreateAccountDTO> {

    private AccountRepository accountRepository;

    @Autowired
    public PreAccountValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void validar(CreateAccountDTO dado) {
        this.validaDocumentNumberExistente(dado);
    }

    public void validaDocumentNumberExistente(CreateAccountDTO createAccountDTO) {
        String documentNumber = createAccountDTO.getDocumentNumber();
        Optional<Account> account = accountRepository.findByDocumentNumber(documentNumber);
        if(account.isPresent()){
            throw new RuntimeException(String.format("Document Number %s already exist.",documentNumber));
        }
    }
}
