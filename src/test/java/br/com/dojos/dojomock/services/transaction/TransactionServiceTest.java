package br.com.dojos.dojomock.services.transaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.dto.transaction.TransactionDTO;
import br.com.dojos.dojomock.entity.enux.OperationType;
import br.com.dojos.dojomock.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author vitor.alves
 */
public class TransactionServiceTest {

    private TransactionService service;

    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        service = new TransactionService(transactionRepository);


    }

    @Test
    public void createTransacaoSucesso(){
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(1L,1,5.0);
        TransactionDTO transactionDTO = service.createTransaction(createTransactionDTO);

        assertThat(transactionDTO.getAccountId(),equalTo(1L));
        assertThat(transactionDTO.getAmount(),equalTo(5.0));
        assertThat(transactionDTO.getOperationType(),equalTo(OperationType.COMPRA_A_VISTA));
    }
}
