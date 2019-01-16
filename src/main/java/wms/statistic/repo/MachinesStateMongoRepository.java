package wms.statistic.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import wms.statistic.dto.MachineStateCurrentDTO;




@Repository
public interface MachinesStateMongoRepository extends MongoRepository<MachineStateCurrentDTO, Integer> {

}
