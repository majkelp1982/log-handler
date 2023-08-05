package pl.smarthouse.loghandler.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.smarthouse.loghandler.model.dto.ErrorDto;
import pl.smarthouse.loghandler.model.dto.InfoDto;
import pl.smarthouse.loghandler.service.LogHandlerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class LogHandlerController {

  LogHandlerService logHandlerService;

  @PostMapping(value = "/error")
  public Mono<ErrorDto> saveError(@RequestBody final ErrorDto errorDto) {
    return logHandlerService.saveError(errorDto);
  }

  @PostMapping(value = "/info")
  public Mono<InfoDto> saveInfo(@RequestBody final InfoDto infoDto) {
    return logHandlerService.saveInfo(infoDto);
  }

  @GetMapping(value = "/findByModuleName")
  public Flux<ErrorDto> findByModuleName(@RequestParam final String moduleName) {
    return logHandlerService.findByModuleName(moduleName);
  }
}
