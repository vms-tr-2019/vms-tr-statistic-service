package wms.statistic.jpa;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
@Entity
@Subselect("SELECT id, year(date) as year, machine_id, sum(quantity * price) as profit from selling AS s join products AS p "
		+ "ON s.product_id = p.product_id group by machine_id, year(date) ")
public class ProfitYearMachineJPA {
	
	@Id
	int id;
	
	public int year;
	@Column(name = "machine_id")
	public int machineId;
	public int profit;
	
	
	public ProfitYearMachineJPA(int id, int year, int machineId, int profit) {
		super();
		this.id = id;
		this.year = year;
		this.machineId = machineId;
		this.profit = profit;
	}
	
	
	
	
	

}
