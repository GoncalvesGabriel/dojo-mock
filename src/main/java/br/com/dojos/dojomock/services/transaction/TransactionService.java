package br.com.dojos.dojomock.services.transaction;

import br.com.dojos.dojomock.repository.TransactionRepository;

/**
 * @author vitor.alves
 */
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
