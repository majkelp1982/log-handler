package pl.smarthouse.loghandler.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.smarthouse.loghandler.model.dao.ErrorDao;
import reactor.core.publisher.Flux;

public interface ErrorRepository extends ReactiveMongoRepository<ErrorDao, String> {
  Flux<ErrorDao> findByModuleName(String moduleName);
}
