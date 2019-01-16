package wms.statistic.jpa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "selling")
public class SensorProductJPA {
  
  @Id
  @GeneratedValue
  int id;
  @Column(name = "date")
  public LocalDate date;
  @Column(name = "machine_id")
  public int machineId;
  @Column(name = "product_id")
  public int productId;
  public int quantity;
 
  public SensorProductJPA(LocalDate date, int machineId, int productId, int quantity) {
    super();
    this.date = date;
    this.machineId = machineId;
    this.productId = productId;
    this.quantity = quantity;
   }
}
