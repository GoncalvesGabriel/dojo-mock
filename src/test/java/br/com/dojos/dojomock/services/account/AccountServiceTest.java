package br.com.dojos.dojomock.services.account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import br.com.dojos.dojomock.services.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService service;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Validator<CreateAccountDTO> accountValidator;

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

    @Test
    public void seDerErroDeValidacaoNaoSalvarEntidade() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        doThrow(RuntimeException.class).when(accountValidator).validar(createAccountDTO);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void validacaoAntesDoSave() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("12345");
        Account account = createAccountDTO.createAccount();

        service.createAccount(createAccountDTO);

        verify(accountRepository, times(1)).save(account);
    }

}
