package br.com.zaipraixis.repositories;

import br.com.zaipraixis.domain.Consultor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorRepository extends MongoRepository<Consultor, String> {
}
