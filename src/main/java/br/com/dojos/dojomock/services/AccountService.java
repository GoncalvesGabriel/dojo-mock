package br.com.dojos.dojomock.services;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vitor.alves
 */
public class AccountService {

    Set<String> documentsNumbersCadastrados;

    public AccountService(){
        documentsNumbersCadastrados = new HashSet<>();
    }

    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        String documentNumber = createAccountDTO.getDocumentNumber();
        validaDocumentNumberExistente(documentNumber);
        Account account = createAccountDTO.createAccount();
        account.setId(1L);
        documentsNumbersCadastrados.add(documentNumber);
        return new AccountDTO(account);
    }

    public void validaDocumentNumberExistente(String documentNumber) {
        if(documentsNumbersCadastrados.contains(documentNumber)){
            throw new RuntimeException("Document Number already exist.");
        }
    }
}
