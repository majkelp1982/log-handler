package pl.smarthouse.loghandler.model.dao;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Error")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorDao {
  @Id private String id;
  private String moduleName;
  private int httpCode;
  private String message;
  private int errorCode;
  private LocalDateTime incomingTime;
  private LocalDateTime outgoingTime;
}
