package br.com.dojos.dojomock.dto.transaction;

import br.com.dojos.dojomock.entity.enux.OperationType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class CreateTransactionDTO {

  private Long accountId;

  private Integer operationType;

  private double amount;

  public OperationType getOperationTypeEnum(){
    return OperationType.getEnum(getOperationType());
  }

  @Builder
  public CreateTransactionDTO(Long accountId, Integer operationType, double amount) {
    this.accountId = accountId;
    this.operationType = operationType;
    this.amount = amount;
  }
}
