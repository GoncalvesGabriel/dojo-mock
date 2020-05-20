package br.com.dojos.dojomock.services;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.equalTo;


public class AccountServiceTest {

    @Test
    public void createAccountSucess(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");

        AccountService service = new AccountService();
        AccountDTO accountLeirbag = service.createAccount(createAccountDTO);


        assertThat(accountLeirbag.getDocumentNumber(),equalTo("12345"));
        assertThat(accountLeirbag.getId(),);
    }




}
