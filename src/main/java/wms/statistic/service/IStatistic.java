package wms.statistic.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import wms.statistic.dto.ProductDTO;

public interface IStatistic {
	
	public Integer getIncomeByPeriod(LocalDate from, LocalDate to);
	public Map<Integer, Integer> getPeriodYearProfit(int fromYear, int toYear);
	public Integer getMachineProfit(int machineId, LocalDate from, LocalDate to);
	public Map<Integer, Integer>  getYearMachineProfitByPeriod(int machineId, int fromYear, int toYear);
	public Integer getProductIncomeByPeriod(String productName, LocalDate from, LocalDate to);
	public Integer getProductSellCountPeriod(String productName, LocalDate from, LocalDate to);
	public Map<String, Integer> getSellProductsByPeriod(String prodName);
	public List<ProductDTO> getMostProfitableProductsByPeriod(LocalDate from, LocalDate to);
	public List<ProductDTO> getLessProfitableProductssByPeriod(LocalDate from, LocalDate to);
	public List<ProductDTO> getMostSellCountProductsByPeriod(LocalDate from, LocalDate to);
	public List<ProductDTO> getLessSellCountProductsByPeriod(LocalDate from, LocalDate to);
	public Map<String, Integer> getCurrentQuantityProdoctInMachine();
	
}
