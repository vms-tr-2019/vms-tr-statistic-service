package wms.statistic.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wms.statistic.dto.ApiConstants;
import wms.statistic.dto.ProductDTO;

@RestController
@RequestMapping(ApiConstants.STATISTICS)
public class StatisticController {

	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	IStatistic service;

	@GetMapping(ApiConstants.GET_INCOME_BY_PERIOD)
	public Integer getIncomeByPeriod(@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		if (to.equals("now")) {
			return service.getIncomeByPeriod(LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getIncomeByPeriod(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}

	@GetMapping(ApiConstants.GET_MACHINE_AVG_PROFIT + "/{machine_id}")
	public Integer etMachineAvgProfit(@PathVariable("machine_id") int machineId,
			@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		if (to.equals("now")) {
			return service.getMachineProfit(machineId, LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getMachineProfit(machineId, LocalDate.parse(from, format), LocalDate.parse(to, format));
	}

	@GetMapping(ApiConstants.GET_PRODUCT_INCOME + "/{product_name}")
	public Integer getProductIncomeByPeriod(@PathVariable("product_name") String productName,
			@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		if (to.equals("now")) {
			return service.getProductIncomeByPeriod(productName, LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getProductIncomeByPeriod(productName, LocalDate.parse(from, format), LocalDate.parse(to, format));
	}

	@GetMapping(ApiConstants.GET_MOST_PROFIT_PRODUCT)
	public List<ProductDTO> getMostProfitableProductsByPeriod(
			@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (to.equals("now")) {
			return service.getMostProfitableProductsByPeriod(LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getMostProfitableProductsByPeriod(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}

	@GetMapping(ApiConstants.GET_LESS_PROFIT_PRODUCT)
	public List<ProductDTO> getLessProfitableProductssByPeriod(
			@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		if (to.equals("now")) {
			return service.getLessProfitableProductssByPeriod(LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getLessProfitableProductssByPeriod(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}

	@GetMapping(ApiConstants.GET_MOST_SELL_COUNT_PRODUCTS)
	public List<ProductDTO> getMostSellCountProductsByPeriod(
			@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		if (to.equals("now")) {
			return service.getMostSellCountProductsByPeriod(LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getMostSellCountProductsByPeriod(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}

	@GetMapping(ApiConstants.GET_LESS_SELL_COUNT_PRODUCTS)
	public List<ProductDTO> getLesstSellCountProductsByPeriod(
			@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		if (to.equals("now")) {
			return service.getLessSellCountProductsByPeriod(LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getLessSellCountProductsByPeriod(LocalDate.parse(from, format), LocalDate.parse(to, format));
	}

	@GetMapping(ApiConstants.GET_PRODUCT_SELL_COUNT + "/{product_name}")
	public Integer getProductSellCountPeriod(@PathVariable("product_name") String productName,
			@RequestParam(name = "from", defaultValue = ApiConstants.GET_START_DAY) String from,
			@RequestParam(name = "to", defaultValue = "now") String to) {
		if (to.equals("now")) {
			return service.getProductSellCountPeriod(productName, LocalDate.parse(from, format), LocalDate.now());
		}
		return service.getProductSellCountPeriod(productName, LocalDate.parse(from, format), LocalDate.parse(to, format));

	}

	@GetMapping(ApiConstants.GET_YEAR_PROFIT)
	public Map<Integer, Integer> getPeriodAvgYearProfit(
			@RequestParam(name = "fromYear", defaultValue = ApiConstants.GET_START_YEAR) String fromYear,
			@RequestParam(name = "toYear", defaultValue = "now") String toYear) {
		if (toYear.equals("now")) {
			return service.getPeriodYearProfit(Integer.parseInt(fromYear), LocalDate.now().getYear());
		}
		return service.getPeriodYearProfit(Integer.parseInt(fromYear), Integer.parseInt(toYear));
	}

	@GetMapping(ApiConstants.GET_YEAR_MACHINE_PROFIT_BY_PERIOD + "/{machine_id}")
	public Map<Integer, Integer> getYearMachineProfitByPeriod(@PathVariable("machine_id") int machineId,
			@RequestParam(name = "fromYear", defaultValue = ApiConstants.GET_START_YEAR) String fromYear,
			@RequestParam(name = "toYear", defaultValue = "now") String toYear) {
		if (toYear.equals("now")) {
			return service.getYearMachineProfitByPeriod(machineId, Integer.parseInt(fromYear), LocalDate.now().getYear());
		}
		return service.getYearMachineProfitByPeriod(machineId, Integer.parseInt(fromYear), Integer.parseInt(toYear));
	}

	@GetMapping(ApiConstants.GET_CURRENT_PRODUCT_STATE_IN_MACHINE)
	public Map<String, Integer> getSellProductsByPeriod() {
		return service.getCurrentQuantityProdoctInMachine();
	}

}
