package pl.smarthouse.loghandler.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.smarthouse.loghandler.model.dao.InfoDao;

public interface InfoRepository extends ReactiveMongoRepository<InfoDao, String> {}
