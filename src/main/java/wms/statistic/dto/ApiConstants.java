package wms.statistic.dto;

public interface ApiConstants {


  //---STATISTICS---//

  String STATISTICS = "";
  String GET_INCOME_BY_PERIOD = "/income/by_period"; // GET
  String GET_YEAR_PROFIT = "/profit/year/by_period"; // GET
  String GET_MACHINE_AVG_PROFIT = "/profit/avg/by_period"; // GET + "/{machineId:int}/by_period"
  String GET_YEAR_MACHINE_PROFIT_BY_PERIOD = "/machine/profit/year"; // GET + "/{machineId:int}/by_period"
  String GET_PRODUCT_INCOME = "/product/income"; // GET + "/{productId:int}/by_period"
  String GET_PRODUCT_SELL_COUNT = "/product/sell"; // GET + "/{productId:int}/by_period"
  String GET_LIST_SELL_PRODUCT = "/product/sell"; // GET maybe SELL !!!!!!!!!!!!!!!
  String GET_MOST_PROFIT_PRODUCT = "/product/profit_most/by_period"; // GET
  String GET_LESS_PROFIT_PRODUCT = "/product/profit_less/by_period"; // GET
  String GET_MOST_SELL_COUNT_PRODUCTS = "/product/count_most/by_period"; // GET maybe change name function to
                                                                              // getMostSellCountProducts
  String GET_LESS_SELL_COUNT_PRODUCTS = "/product/count_less/by_period"; // GET maybe change name function to
                                                                              // getLessSellCountProducts
  
  String GET_CURRENT_PRODUCT_STATE_IN_MACHINE = "/product/current"; // GET maybe + "?product_id={product_id:int}
                                                                         // ande change method name
  String GET_START_DAY = "2010-01-01";
  String GET_START_YEAR = "2010";

}
