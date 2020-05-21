package br.com.dojos.dojomock.services.transaction;

import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.repository.AccountRepository;
import br.com.dojos.dojomock.repository.TransactionRepository;
import br.com.dojos.dojomock.services.Validator;
import br.com.dojos.dojomock.services.account.AccountService;
import br.com.dojos.dojomock.services.account.PreAccountValidator;
import org.junit.Before;
import org.mockito.Mockito;

/**
 * @author vitor.alves
 */
public class TransactionServiceTest {

    private TransactionService service;

    private TransactionRepository accountRepository;

    @Before
    public void setUp() {
        accountRepository = Mockito.mock(TransactionRepository.class);
        service = new TransactionService(accountRepository, accountValidator);
    }
}
