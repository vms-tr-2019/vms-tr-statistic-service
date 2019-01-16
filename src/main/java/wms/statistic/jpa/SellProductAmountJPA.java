package wms.statistic.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
@Entity
@Subselect("select product_name, sum(quantity) as amount from selling as s join products as p "
		+ "on s.product_id=p.product_id group by product_name")
public class SellProductAmountJPA {
	
	@Id
	@Column(name = "product_name")
	String productName;
	int amount;
	
	public SellProductAmountJPA(String productName, int amount) {
		super();
		this.productName = productName;
		this.amount = amount;
	}
	
	
	

}
