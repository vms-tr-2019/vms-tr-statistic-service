package wms.statistic.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import wms.statistic.jpa.ProfitYearMachineJPA;

public interface CollectionSellData extends JpaRepository<ProfitYearMachineJPA, Integer>{

	List<ProfitYearMachineJPA> findByMachineIdAndYearBetween(int machineId, int fromYear, int toYear);

}
