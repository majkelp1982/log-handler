package pl.smarthouse.loghandler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import pl.smarthouse.loghandler.configuration.LogConfiguration;
import pl.smarthouse.loghandler.model.dto.BaseDto;
import pl.smarthouse.loghandler.model.dto.ErrorDto;
import pl.smarthouse.loghandler.model.dto.InfoDto;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LogService {
  LogConfiguration logConfiguration = new LogConfiguration();

  public Mono<BaseDto> error(final ErrorDto errorDto) {
    log.error(errorDto.toString());
    return logConfiguration
        .webClient()
        .post()
        .uri("/error")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(errorDto)
        .exchangeToMono(clientResponse -> processResponse(ErrorDto.class, clientResponse));
  }

  public Mono<BaseDto> info(final InfoDto infoDto) {
    log.info(infoDto.toString());
    return logConfiguration
        .webClient()
        .post()
        .uri("/info")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(infoDto)
        .exchangeToMono(clientResponse -> processResponse(InfoDto.class, clientResponse));
  }

  private Mono<BaseDto> processResponse(final Class clazz, final ClientResponse clientResponse) {
    if (clientResponse.statusCode().is2xxSuccessful()) {
      return clientResponse.bodyToMono(clazz);
    } else {
      return Mono.error(
          new RuntimeException(
              String.format(
                  "Error on communication with log service. Status code: %s, Reason: %s",
                  clientResponse.statusCode(),
                  clientResponse.body((inputMessage, context) -> inputMessage.getBody()))));
    }
  }
}
