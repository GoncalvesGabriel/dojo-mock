package br.com.dojos.dojomock.services.transaction;

import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.dto.transaction.TransactionDTO;
import br.com.dojos.dojomock.repository.TransactionRepository;

/**
 * @author vitor.alves
 */
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        TransactionDTO transactionDTO = new TransactionDTO();
        return new TransactionDTO();
    }
}
