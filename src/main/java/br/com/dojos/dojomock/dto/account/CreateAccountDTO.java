package br.com.dojos.dojomock.dto.account;

import br.com.dojos.dojomock.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class CreateAccountDTO {

  private String documentNumber;

  public Account createAccount() {
    Account account = new Account();
    account.setDocumentNumber(this.getDocumentNumber());
    return account;
  }
}
