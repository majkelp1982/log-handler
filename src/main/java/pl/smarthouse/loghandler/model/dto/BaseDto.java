package pl.smarthouse.loghandler.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BaseDto {
  private String id;
  private String moduleName;
  private String message;
}
