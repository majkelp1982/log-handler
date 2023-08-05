package pl.smarthouse.loghandler.repository.reactive;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.smarthouse.loghandler.model.dao.ErrorDao;
import pl.smarthouse.loghandler.model.dto.ErrorDto;
import pl.smarthouse.loghandler.repository.ErrorRepository;
import pl.smarthouse.loghandler.utils.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Repository
public class ReactiveErrorRepository {

  ErrorRepository errorRepository;

  public Mono<ErrorDto> save(final ErrorDao errorDao) {
    return errorRepository.save(errorDao).map(ModelMapper::toErrorDto);
  }

  public Flux<ErrorDto> findByModuleName(final String moduleName) {
    return errorRepository.findByModuleName(moduleName).map(ModelMapper::toErrorDto);
  }
}
