package br.com.dojos.dojomock.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class AccountServiceTest {

    private AccountService service;

    AccountRepository accountRepository = Mockito.mock(AccountRepository.class);

    @Before
    public void setUp() {
        service = new AccountService(accountRepository);
    }

    @Test
    public void createAccountSucess() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");
        Account account = createAccountDTO.createAccount();

        AccountDTO accountLeirbag = service.createAccount(createAccountDTO);
        verify(accountRepository).save(account);

        assertThat(accountLeirbag.getDocumentNumber(), equalTo("12345"));
    }

    @Test(expected = RuntimeException.class)
    public void naoPermiteDuasContasMesmoDocumento() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");

        service.createAccount(createAccountDTO);
        service.createAccount(createAccountDTO);
    }
}
