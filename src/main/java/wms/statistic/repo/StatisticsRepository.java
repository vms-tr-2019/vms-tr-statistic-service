package wms.statistic.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wms.statistic.jpa.SensorProductJPA;



public interface StatisticsRepository extends JpaRepository<SensorProductJPA, Integer> {

	@Query(value ="SELECT sum(quantity * price) FROM selling AS s join products AS p "
			+ "ON s.product_id = p.product_id where date BETWEEN :from AND :to", nativeQuery=true)
	Integer selectIncomeByPeriod(@Param("from") LocalDate from, @Param("to") LocalDate to);

	
	
	@Query(value ="SELECT sum(quantity * price) FROM selling AS s join products AS p ON s.product_id = p.product_id "
			+ "where machine_id =:id AND (date BETWEEN :from AND :to)", nativeQuery=true)
	Integer selectProfitByMachineByPeriod(@Param("id")int machineId, @Param("from") LocalDate from, 
			@Param("to") LocalDate to);


	@Query(value ="SELECT sum(quantity * price) FROM selling AS s join products AS p ON s.product_id = p.product_id "
			+ "where p.product_name =:name AND (date BETWEEN :from AND :to)", nativeQuery=true)
	Integer selectProfitByProductByPeriod(@Param("name") String name, @Param("from") LocalDate from, 
			@Param("to") LocalDate to);


	@Query(value ="SELECT max(summ * price) FROM (select product_id, sum(quantity) as summ from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s "
			+ "join products AS p ON s.product_id = p.product_id", nativeQuery=true)	
	Integer selectMaxProfitByProduct(@Param("from") LocalDate from, @Param("to") LocalDate to);
	
	@Query(value ="SELECT min(summ * price) FROM (select product_id, sum(quantity) as summ from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s "
			+ "join products AS p ON s.product_id = p.product_id", nativeQuery=true)	
	Integer selectMinProfitByProduct(@Param("from") LocalDate from, @Param("to") LocalDate to);


	@Query(value ="SELECT s.product_id FROM (select product_id, sum(quantity) as summ from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s "
			+ "join products AS p ON s.product_id = p.product_id where (summ * price) = :max", nativeQuery=true)	
	List<Integer> getMostProfitableProductByPeriod(@Param("max")Integer max, 
			@Param("from") LocalDate from, @Param("to") LocalDate to);

	@Query(value ="SELECT s.product_id FROM (select product_id, sum(quantity) as summ from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s "
			+ "join products AS p ON s.product_id = p.product_id where (summ * price) = :min", nativeQuery=true)
	List<Integer>  getLessProfitableProductByPeriod(@Param("min")Integer min, 
			@Param("from") LocalDate from, @Param("to") LocalDate to);


	@Query(value ="SELECT max(count) FROM (select product_id, sum(quantity) as count from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s", nativeQuery=true)
	Integer selectMaxCountSell(@Param("from") LocalDate from, @Param("to") LocalDate to);


	@Query(value ="SELECT product_id FROM (select product_id, sum(quantity) as count from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s "
			+ "where count = :max", nativeQuery=true)
	List<Integer> selectMostSellCountProduct(@Param("max")Integer max, 
			@Param("from") LocalDate from, @Param("to") LocalDate to);


	@Query(value ="SELECT min(count) FROM (select product_id, sum(quantity) as count from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s", nativeQuery=true)
	Integer selectMinCountSell(@Param("from") LocalDate from, @Param("to") LocalDate to);


	@Query(value ="SELECT product_id FROM (select product_id, sum(quantity) as count from selling "
			+ "where (date BETWEEN :from AND :to) group by product_id) as s "
			+ "where count = :min", nativeQuery=true)
	List<Integer> selectLessSellCountProduct(@Param("min")Integer min, 
			@Param("from") LocalDate from, @Param("to") LocalDate to);


	@Query(value ="select count from (select product_id, sum(quantity) as count from selling where (date BETWEEN :from AND :to) "
			+ "group by product_id) as s join products as p  ON s.product_id = p.product_id "
			+ "where product_name = :name ", nativeQuery=true)
	Integer getCountSellProductByPeriod(@Param("name")String nameProduct, 
			@Param("from") LocalDate from, @Param("to") LocalDate to);



	
	

}
