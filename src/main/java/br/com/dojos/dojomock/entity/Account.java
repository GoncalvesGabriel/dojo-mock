package br.com.dojos.dojomock.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Account")
@NoArgsConstructor
public @Data
class Account {

  @Id
  @Column(name = "Account_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "Document_Number", unique = true)
  private String documentNumber;

  @Builder
  public Account(Long id, String documentNumber) {
    this.id = id;
    this.documentNumber = documentNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(getDocumentNumber(), account.getDocumentNumber());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDocumentNumber());
  }
}
