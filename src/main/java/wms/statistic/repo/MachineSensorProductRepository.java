package wms.statistic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import wms.statistic.jpa.MachineProductSensorJPA;

public interface MachineSensorProductRepository extends JpaRepository<MachineProductSensorJPA, Integer>{

	List<MachineProductSensorJPA> findByMachineId(int machineId);

}
