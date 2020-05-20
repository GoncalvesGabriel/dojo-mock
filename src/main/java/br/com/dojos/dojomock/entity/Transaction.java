package br.com.dojos.dojomock.entity;


import br.com.dojos.dojomock.entity.enux.OperationType;
import br.com.dojos.dojomock.entity.enux.converter.OperationTypeConverter;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Entity
@Table(name =  "Transaction")
@NoArgsConstructor
public @Data
class Transaction {

  @Id
  @Column(name = "Transaction_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "Account_ID")
  private Account account;

  @Column(name = "OperationType_ID")
  @Convert(converter = OperationTypeConverter.class)
  private OperationType operationType;

  @Column(name = "Amount")
  private double amount;

  @Column(name = "EventDate")
  @Convert( converter = LocalDateTimeConverter.class)
  private LocalDateTime eventDate;

  public Transaction(Account account, OperationType operationType, double amount, LocalDateTime eventDate) {
    this(null, account, operationType, amount, eventDate);
  }

  @Transient
  public Long getNumberAccount(){
     return this.getAccount().getId();
  }

  @Builder
  public Transaction(Long id, Account account, OperationType operationType, double amount, LocalDateTime eventDate) {
    this.id = id;
    this.account = account;
    this.operationType = operationType;
    this.amount = amount;
    this.eventDate = eventDate;
  }
}