package br.com.dojos.dojomock.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import org.junit.Test;


public class AccountServiceTest {

    @Test
    public void createAccountSucess() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");

        AccountService service = new AccountService();
        AccountDTO accountLeirbag = service.createAccount(createAccountDTO);

        assertThat(accountLeirbag.getDocumentNumber(), equalTo("12345"));
        assertThat(accountLeirbag.getId(), equalTo(1L));
    }

    @Test(expected = RuntimeException.class)
    public void naoPermiteDuasContasMesmoDocumento(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");

        AccountService service = new AccountService();

        service.createAccount(createAccountDTO);
        service.createAccount(createAccountDTO);


    }


}
