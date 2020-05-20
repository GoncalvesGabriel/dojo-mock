package br.com.dojos.dojomock.entity.enux.converter;

import br.com.dojos.dojomock.entity.enux.OperationType;
import javax.persistence.AttributeConverter;

public class OperationTypeConverter implements AttributeConverter<OperationType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(OperationType operationType) {
    return operationType.getId();
  }

  @Override
  public OperationType convertToEntityAttribute(Integer id) {
    return OperationType.getEnum(id);
  }
}
