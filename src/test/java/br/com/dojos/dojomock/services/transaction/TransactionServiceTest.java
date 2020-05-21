package br.com.dojos.dojomock.services.transaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.dto.transaction.TransactionDTO;
import br.com.dojos.dojomock.entity.Account;
import br.com.dojos.dojomock.entity.Transaction;
import br.com.dojos.dojomock.entity.enux.OperationType;
import br.com.dojos.dojomock.repository.TransactionRepository;
import br.com.dojos.dojomock.services.account.AccountService;
import java.time.DayOfWeek;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author vitor.alves
 */
public class TransactionServiceTest {

    private TransactionService service;

    private TransactionRepository transactionRepository;

    private AccountService accountService;

    @Before
    public void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        accountService = Mockito.mock(AccountService.class);
        service = new TransactionService(transactionRepository, accountService);
    }

    @Test
    public void createTransacaoSucesso() {
        when(accountService.findById(1L)).thenReturn(new Account(1L, null));
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(1L, 1, 5.0);
        TransactionDTO transactionDTO = service.createTransaction(createTransactionDTO);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        assertThat(transactionDTO.getAccountId(), equalTo(1L));
        assertThat(transactionDTO.getAmount(), equalTo(5.0));
        assertThat(transactionDTO.getOperationType(), equalTo(OperationType.COMPRA_A_VISTA));
    }

    @Test
    public void createTransacaoDiaNaoUtil() {
        when(accountService.findById(1L)).thenReturn(new Account(1L, null));
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(1L, 1, 5.0);
        TransactionDTO transactionDTO = service.createTransaction(createTransactionDTO);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        assertThat(transactionDTO.getAccountId(), equalTo(1L));
        assertThat(transactionDTO.getAmount(), equalTo(5.0));
        assertThat(transactionDTO.getOperationType(), equalTo(OperationType.COMPRA_A_VISTA));
        assertThat(transactionDTO.getEfectiveDate().getDayOfWeek(), equalTo(DayOfWeek.MONDAY));
    }


}
