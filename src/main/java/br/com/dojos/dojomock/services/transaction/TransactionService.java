package br.com.dojos.dojomock.services.transaction;

import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.dto.transaction.TransactionDTO;
import br.com.dojos.dojomock.entity.Transaction;
import br.com.dojos.dojomock.repository.TransactionRepository;
import br.com.dojos.dojomock.services.account.AccountService;
import br.com.dojos.dojomock.utils.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * @author vitor.alves
 */
@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    private AccountService accountService;

    private Clock clock;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.clock = clock;
    }

    public TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        Transaction transaction = new Transaction(accountService.findById(createTransactionDTO.getAccountId()),
            createTransactionDTO.getOperationTypeEnum(), createTransactionDTO.getAmount(), clock.devolveHoje(), devolveDiaEfetivo());
        transactionRepository.save(transaction);
        return new TransactionDTO(transaction);
    }

    private LocalDateTime devolveDiaEfetivo() {
        LocalDateTime hoje = clock.devolveHoje();
        if (hoje.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return hoje.plusDays(2);
        }
        if (hoje.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return hoje.plusDays(1);
        }
        return hoje;
    }
}
