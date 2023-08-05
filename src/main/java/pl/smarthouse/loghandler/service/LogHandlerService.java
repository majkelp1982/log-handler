package pl.smarthouse.loghandler.service;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.smarthouse.loghandler.model.dao.ErrorDao;
import pl.smarthouse.loghandler.model.dao.InfoDao;
import pl.smarthouse.loghandler.model.dto.ErrorDto;
import pl.smarthouse.loghandler.model.dto.InfoDto;
import pl.smarthouse.loghandler.repository.reactive.ReactiveErrorRepository;
import pl.smarthouse.loghandler.repository.reactive.ReactiveInfoRepository;
import pl.smarthouse.loghandler.utils.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class LogHandlerService {

  private static final String SUCCESS = "Action: {} successful. Object: {}";
  private static final String ERROR = "Action: {} error, Object: {}, reason: {}";
  private static final String SAVE_ERROR = "saveError";
  private static final String SAVE_INFO = "saveInfo";

  ReactiveErrorRepository reactiveErrorRepository;
  ReactiveInfoRepository reactiveInfoRepository;

  public Mono<ErrorDto> saveError(final ErrorDto errorDto) {
    final ErrorDao errorDao = ModelMapper.toErrorDao(errorDto);
    errorDao.setIncomingTime(LocalDateTime.now());
    errorDao.setOutgoingTime(null);
    return reactiveErrorRepository
        .save(errorDao)
        .doOnSuccess(ignore -> log.info(SUCCESS, SAVE_INFO, errorDto))
        .doOnError(throwable -> log.error(ERROR, SAVE_ERROR, errorDto, throwable.getMessage()));
  }

  public Mono<InfoDto> saveInfo(final InfoDto infoDto) {
    final InfoDao infoDao = ModelMapper.toInfoDao(infoDto);
    return reactiveInfoRepository
        .save(infoDao)
        .doOnSuccess(ignore -> log.info(SUCCESS, SAVE_INFO, infoDto))
        .doOnError(throwable -> log.error(ERROR, SAVE_ERROR, infoDto, throwable.getMessage()));
  }

  public Flux<ErrorDto> findByModuleName(final String moduleName) {
    return reactiveErrorRepository.findByModuleName(moduleName);
  }
}
