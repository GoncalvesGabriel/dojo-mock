package br.com.dojos.dojomock.dto.transaction;

import br.com.dojos.dojomock.entity.Transaction;
import br.com.dojos.dojomock.entity.enux.OperationType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
class TransactionDTO {

  private Long id;

  private Long accountId;

  private OperationType operationType;

  private double amount;

  private LocalDateTime eventDate;

  private LocalDateTime efectiveDate;

  public TransactionDTO(Transaction transaction) {
    this(transaction.getId(), transaction.getNumberAccount(), transaction.getOperationType(), transaction.getAmount(), transaction.getEventDate(), transaction.getEfectiveDate());
  }

  @Builder
  public TransactionDTO(Long id, Long accountId, OperationType operationType, double amount, LocalDateTime eventDate, LocalDateTime efectiveDate) {
    this.id = id;
    this.accountId = accountId;
    this.operationType = operationType;
    this.amount = amount;
    this.eventDate = eventDate;
    this.efectiveDate = efectiveDate;
  }
}
