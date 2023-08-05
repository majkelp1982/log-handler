package pl.smarthouse.loghandler.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@ToString(callSuper = true)
public class ErrorDto extends BaseDto {
  private int httpCode;
  private int errorCode;
}
