package br.com.dojos.dojomock.dto.account;

import br.com.dojos.dojomock.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
class AccountDTO {

  private Long id;

  private String documentNumber;

  public AccountDTO(Account account) {
    this.id = account.getId();
    this.documentNumber = account.getDocumentNumber();
  }

  @Builder
  public AccountDTO(Long id, String documentNumber) {
    this.id = id;
    this.documentNumber = documentNumber;
  }
}
