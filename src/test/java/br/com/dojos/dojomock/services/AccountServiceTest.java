package br.com.dojos.dojomock.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import br.com.dojos.dojomock.services.account.AccountService;
import br.com.dojos.dojomock.services.account.PreAccountValidator;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;


public class AccountServiceTest {

    private AccountService service;

    private AccountRepository accountRepository;

    private Validator<CreateAccountDTO> accountValidator;

    @Before
    public void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountValidator = Mockito.mock(PreAccountValidator.class);
        service = new AccountService(accountRepository, accountValidator);
    }

    @Test
    public void createAccountSucess() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");
        Account account = createAccountDTO.createAccount();
        ArgumentCaptor<Account> argumentCaptor = ArgumentCaptor.forClass(Account.class);

        AccountDTO accountLeirbag = service.createAccount(createAccountDTO);

        verify(accountRepository).save(argumentCaptor.capture());
        Account value = argumentCaptor.getValue();
        assertThat(value, equalTo(account));
        assertThat(accountLeirbag.getDocumentNumber(), equalTo("12345"));
    }

    @Test(expected = RuntimeException.class)
    public void naoPermiteDuasContasMesmoDocumento() {

    }

    @Test
    public void validacaoAntesDoSave(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");
        Account account = createAccountDTO.createAccount();

        service.createAccount(createAccountDTO);

        InOrder inOrder = inOrder(accountRepository);
        inOrder.verify(accountRepository).findByDocumentNumber(anyString());
        inOrder.verify(accountRepository).save(account);

    }

}
