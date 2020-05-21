package br.com.dojos.dojomock.services.transaction;

import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.dto.transaction.TransactionDTO;
import br.com.dojos.dojomock.entity.Transaction;
import br.com.dojos.dojomock.repository.TransactionRepository;
import br.com.dojos.dojomock.services.account.AccountService;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * @author vitor.alves
 */
public class TransactionService {

    private TransactionRepository transactionRepository;

    private AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        Transaction transaction = new Transaction(accountService.findById(createTransactionDTO.getAccountId()),
            createTransactionDTO.getOperationTypeEnum(), createTransactionDTO.getAmount(), LocalDateTime.now(), devolveDiaEfetivo());
        transactionRepository.save(transaction);
        return new TransactionDTO(transaction);
    }

    private LocalDateTime devolveDiaEfetivo() {
        LocalDateTime hoje = LocalDateTime.now();
        if (hoje.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return hoje.plusDays(2);
        }
        if (hoje.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return hoje.plusDays(1);
        }
        return hoje;
    }
}
