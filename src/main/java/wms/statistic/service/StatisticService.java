package wms.statistic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wms.statistic.dto.MachineStateCurrentDTO;
import wms.statistic.dto.ProductDTO;
import wms.statistic.jpa.MachineProductSensorJPA;
import wms.statistic.jpa.ProductJPA;
import wms.statistic.jpa.ProfitYearMachineJPA;
import wms.statistic.jpa.SellProductAmountJPA;
import wms.statistic.repo.CollectionSellData;
import wms.statistic.repo.MachineSensorProductRepository;
import wms.statistic.repo.MachinesStateMongoRepository;
import wms.statistic.repo.ProductsRepository;
import wms.statistic.repo.SellProductAmountRepository;
import wms.statistic.repo.StatisticsRepository;

@Service
public class StatisticService implements IStatistic {

	@Autowired
	StatisticsRepository repo;

	@Autowired
	CollectionSellData collect;

	@Autowired
	ProductsRepository prod;

	@Autowired
	SellProductAmountRepository sellProd;

	@Autowired
	MachinesStateMongoRepository stateMongo;

	@Autowired
	MachineSensorProductRepository sensorProd;

	@Override
	public Integer getIncomeByPeriod(LocalDate from, LocalDate to) {
		return repo.selectIncomeByPeriod(from, to);
	}

	@Override
	public Map<Integer, Integer> getPeriodYearProfit(int fromYear, int toYear) {
		List<ProfitYearMachineJPA> list = collect.findAll();
		return list.stream().filter(jpa -> jpa.year >= fromYear && jpa.year <= toYear)
				.collect(Collectors.groupingBy(rec -> rec.getYear(), Collectors.summingInt(rec -> rec.getProfit())));

	}

	@Override
	public Integer getMachineProfit(int machineId, LocalDate from, LocalDate to) {
		return repo.selectProfitByMachineByPeriod(machineId, from, to);
	}

	@Override
	public Map<Integer, Integer> getYearMachineProfitByPeriod(int machineId, int fromYear, int toYear) {
		List<ProfitYearMachineJPA> listJpa = collect.findByMachineIdAndYearBetween(machineId, fromYear, toYear);
		Map<Integer, Integer> mapJpa = new HashMap<>();
		if (listJpa.isEmpty())
			return mapJpa;

		listJpa.forEach(jpa -> mapJpa.put(jpa.getYear(), jpa.getProfit()));
		return mapJpa;
	}

	@Override
	public Integer getProductIncomeByPeriod(String productName, LocalDate from, LocalDate to) {
		return repo.selectProfitByProductByPeriod(productName, from, to);
	}

	@Override
	public Integer getProductSellCountPeriod(String productName, LocalDate from, LocalDate to) {
		return repo.getCountSellProductByPeriod(productName, from, to);
	}

	@Override
	public Map<String, Integer> getSellProductsByPeriod(String prodName) {
		Map<String, Integer> mapSell = new HashMap<>();
		if (prodName.equals("null")) {
			sellProd.findAll().forEach(rec -> mapSell.put(rec.getProductName(), rec.getAmount()));
			return mapSell;
		}
		SellProductAmountJPA jpa = sellProd.findById(prodName).orElse(null);
		if (jpa == null)
			return new HashMap<>();

		mapSell.put(jpa.getProductName(), jpa.getAmount());
		return mapSell;
	}

	@Override
	public List<ProductDTO> getMostProfitableProductsByPeriod(LocalDate from, LocalDate to) {
		Integer max = repo.selectMaxProfitByProduct(from, to);
		if (max == null)
			return new ArrayList<>();

		List<Integer> numberMost = repo.getMostProfitableProductByPeriod(max, from, to);
		return prod.findAllById(numberMost).stream().map(ProductJPA::toProductDTO).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getLessProfitableProductssByPeriod(LocalDate from, LocalDate to) {
		Integer min = repo.selectMinProfitByProduct(from, to);
		if (min == null)
			return new ArrayList<>();
		List<Integer> numberLess = repo.getLessProfitableProductByPeriod(min, from, to);
		return prod.findAllById(numberLess).stream().map(ProductJPA::toProductDTO).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getMostSellCountProductsByPeriod(LocalDate from, LocalDate to) {
		Integer maxCount = repo.selectMaxCountSell(from, to);
		if (maxCount == null)
			return new ArrayList<>();
		List<Integer> listNumber = repo.selectMostSellCountProduct(maxCount, from, to);
		return prod.findAllById(listNumber).stream().map(ProductJPA::toProductDTO).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getLessSellCountProductsByPeriod(LocalDate from, LocalDate to) {
		Integer minCount = repo.selectMinCountSell(from, to);
		List<Integer> listNumber = repo.selectLessSellCountProduct(minCount, from, to);
		return prod.findAllById(listNumber).stream().map(ProductJPA::toProductDTO).collect(Collectors.toList());
	}

	@Override
	public Map<String, Integer> getCurrentQuantityProdoctInMachine() {
		Map<String, Integer> listData = new HashMap<>();
		List<MachineStateCurrentDTO> listState = stateMongo.findAll();
		for (MachineStateCurrentDTO state : listState) {
			List<MachineProductSensorJPA> listSePro = sensorProd.findByMachineId(state.getMachineId());

			putInMapProdAmount(state.getSensorsData(), listSePro, listData);
		}
		return listData;
	}

	private void putInMapProdAmount(Map<Integer, Integer> sensorsData, List<MachineProductSensorJPA> listSePro,
			Map<String, Integer> listData) {
		Integer value = null;
		for (MachineProductSensorJPA jpa : listSePro) {
			value = sensorsData.get(jpa.getSensorId());
			if (value != null) {
				listData.merge(jpa.getProductName(), value, (v1, v2) -> v1 + v2);
			}
		}

	}

}
