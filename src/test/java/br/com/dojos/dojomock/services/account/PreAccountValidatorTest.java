package br.com.dojos.dojomock.services.account;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.repository.AccountRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PreAccountValidatorTest {

    @Rule
    public ExpectedException rules = ExpectedException.none();

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private PreAccountValidator validator;

    @Before
    public void setup() {
        accountRepository = Mockito.mock(AccountRepository.class);
        validator = new PreAccountValidator(accountRepository);
    }

    @Test
    public void validacaoComSucesso() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("1307");
        Mockito.when(accountRepository.findByDocumentNumber(anyString()))
            .thenReturn(Optional.empty());
        validator.validar(createAccountDTO);
        verify(accountRepository, times(1)).findByDocumentNumber("1307");
    }

    @Test
    public void validacaoComErro() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setDocumentNumber("1307");
        Mockito.when(accountRepository.findByDocumentNumber(anyString()))
            .thenReturn(Optional.of(createAccountDTO.createAccount()));
        rules.expectMessage("Document Number 1307 already exist.");
        validator.validar(createAccountDTO);

    }

}