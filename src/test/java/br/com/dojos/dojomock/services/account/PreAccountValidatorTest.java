package br.com.dojos.dojomock.services.account;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PreAccountValidatorTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private PreAccountValidator validator;

    @Before
    public void setup(){
        accountRepository = Mockito.mock(AccountRepository.class);
        validator = new PreAccountValidator(accountRepository);
    }

    @Test
    public void validacaoComSucesso(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("1307");
        Mockito.when(accountRepository.findByDocumentNumber(anyString()))
            .thenReturn(Optional.empty());
        validator.validar(createAccountDTO);
        verify(accountRepository,times(1)).findByDocumentNumber("1307");
    }

    @Test(expected = RuntimeException.class)
    public void validacaoComErro(){
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("1307");
        Mockito.when(accountRepository.findByDocumentNumber(anyString()))
            .thenReturn(Optional.of(new Account()));
        validator.validar(createAccountDTO);

    }

}