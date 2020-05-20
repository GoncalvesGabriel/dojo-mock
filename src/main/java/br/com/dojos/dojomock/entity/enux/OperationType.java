package br.com.dojos.dojomock.entity.enux;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

public @Getter
enum OperationType {

  COMPRA_A_VISTA(1, "COMPRA A VISTA"),

  COMPRA_PARCELADA(2, "COMPRA PARCELADA"),

  SAQUE(3, "SAQUE"),

  PAGAMENTO(4, "PAGAMENTO");

  private Integer id;

  private String description;

  OperationType(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public static OperationType getEnum(Integer id) {
    for (OperationType value : OperationType.values()) {
      if (value.getId().equals(id)) {
        return value;
      }
    }
    throw new IllegalArgumentException(String.format("Tipo de operação (%s) não suportado", id));
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

  public List<OperationType> getOperationTypesIncome(){
    return Arrays.asList(PAGAMENTO);
  }

  public List<OperationType> getOperationTypesOutcome(){
    return Arrays.asList(COMPRA_PARCELADA, COMPRA_A_VISTA, SAQUE);
  }

  public boolean isIncome() {
    return getOperationTypesIncome().contains(this);
  }

  public boolean isOutcome() {
    return getOperationTypesOutcome().contains(this);
  }
}
